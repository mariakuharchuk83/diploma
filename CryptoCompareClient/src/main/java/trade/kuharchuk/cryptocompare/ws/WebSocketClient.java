package trade.kuharchuk.cryptocompare.ws;

import trade.kuharchuk.cryptocompare.client.HttpClientBuilder;
import trade.kuharchuk.cryptocompare.client.TrafficLogger;
import trade.kuharchuk.cryptocompare.exception.WSException;
import trade.kuharchuk.cryptocompare.CryptoCompareClientConstant;
import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import trade.kuharchuk.cryptocompare.ws.message.ApiErrorMessage;
import trade.kuharchuk.cryptocompare.ws.message.BaseResponse;
import trade.kuharchuk.cryptocompare.ws.message.MessageType;
import trade.kuharchuk.cryptocompare.ws.message.WSAction;
import trade.kuharchuk.cryptocompare.ws.message.WSRequest;
import trade.kuharchuk.cryptocompare.ws.message.WelcomeMessage;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Closeable;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.WS_CLOSE_GRACEFULLY_CODE;
import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.WS_CLOSE_GRACEFULLY_MSG;
import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.WS_CLOSE_ON_ERROR_CODE;
import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.WS_RECONNECTION_DELAY_MS;
import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.WS_STALE_DETECTION_WINDOW_SEC;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

/**
 * CryptoCompare WebSocket client <a
 * href="https://developers.cryptocompare.com/documentation/legacy-websockets/introduction">...</a>
 * Client is capable of handling failures as well as connection and subscription errors. Reconnect
 * automatically if server closes connection. Regularly do a healthcheck to ensure the connection is
 * not stale. Does not support dynamic subscription/unsubscription as for now.
 *
 * @param <T> response type for your channel. It should correspond to your subscription type
 */
@Slf4j
public class WebSocketClient<T extends BaseResponse> extends WebSocketListener
    implements Closeable, WebSocketConnection<T> {
  private static final String TIMER_THREAD_NAME = "-timer";
  private static final long MILLIS = 1000;

  private final CryptoCompareParams config;
  private final OkHttpClient httpClient;
  private final ObjectMapper objectMapper;
  private final String logPrefix;
  private final Request connectionRequest;
  private final WebSocketCallback<T> callback;

  @Getter private Set<Channel> channels;
  @Getter private Instant lastReceivedTime;
  @Getter private WSState state;
  private Set<MessageType> subscriptionTypes;
  private WebSocket webSocket;
  private Timer timer;
  private CompletableFuture<WelcomeMessage> waitForConnectionFuture;

  public WebSocketClient(final CryptoCompareParams config, final WebSocketCallback<T> callback) {
    this.config = config;
    this.httpClient = new HttpClientBuilder(config).buildClient();
    this.callback = callback;
    this.connectionRequest = buildRequestFromHost(CryptoCompareClientConstant.WS_BASE_URL);
    this.objectMapper = configureObjectMapper();
    this.logPrefix = "[ws-" + IdGenerator.getNextId() + "]";
    this.state = WSState.IDLE;
  }

  /**
   * Initialize the WS connection and subscribes to channels.
   *
   * @param subscriptionsSet channels to subscribe to
   * @throws WSException if subscription failed
   */
  @Override
  public void connect(final Set<Channel> subscriptionsSet) throws WSException {
    if (state != WSState.IDLE) {
      throw new IllegalStateException(logPrefix + " Already connected to channels: " + channels);
    }

    channels = Collections.unmodifiableSet(subscriptionsSet);
    log.info("{} Connecting to channels {} ...", logPrefix, channels);
    subscriptionTypes = channels.stream().map(Channel::getType).collect(Collectors.toSet());

    state = WSState.CONNECTING;
    webSocket = httpClient.newWebSocket(connectionRequest, this);
    subscribe(channels);
    state = WSState.CONNECTED;

    this.timer = new Timer(logPrefix + TIMER_THREAD_NAME, true);
    timer.scheduleAtFixedRate(
        new HealthcheckTask(),
        config.getWsHealthcheckIntervalSec() * MILLIS,
        config.getWsHealthcheckIntervalSec() * MILLIS);
  }

  /** Close the WebSocket connection gracefully. */
  @Override
  public void close() {
    state = WSState.IDLE;
    close(WS_CLOSE_GRACEFULLY_CODE, WS_CLOSE_GRACEFULLY_MSG);
  }

  /**
   * Closes previous and opens a new WS connection, followed be subscription to the original
   * channels. Does have a recommended delay between closing and opening a new one.
   *
   * @param retryCount number of reconnection attempts
   * @return true if reconnected successfully, false otherwise
   */
  @Override
  public boolean reconnect(final int retryCount) {
    boolean success = false;
    int counter = 0;
    while (!success && counter++ < retryCount) {
      try {
        log.info("{} Try to reconnect. Attempt #{}", logPrefix, counter);
        this.close();
        Thread.sleep(WS_RECONNECTION_DELAY_MS);
        this.connect(this.channels);
        success = true;
      } catch (Exception e) {
        log.error(
            "{} [Connection error] Error while reconnecting: {}", logPrefix, e.getMessage(), e);
      }
    }
    return success;
  }

  @Override
  public void onMessage(@NotNull final WebSocket ws, @NotNull final String text) {
    super.onMessage(ws, text);
    lastReceivedTime = Instant.now();
    try {
      if (config.isLogTraffic()) {
        TrafficLogger.WS_TRAFFIC_LOGGER.trace("{} message: {}", logPrefix, text);
      }

      final BaseResponse response = objectMapper.readValue(text, BaseResponse.class);
      final MessageType type = response.getType();
      if (subscriptionTypes.contains(type)) {
        callback.onResponse((T) response);
      } else if (type == MessageType.STREAMER_WELCOME && state == WSState.CONNECTING) {
        waitForConnectionFuture.complete((WelcomeMessage) response);
      } else if (MessageType.ERROR_TYPES.contains(type)) {
        final ApiErrorMessage error = (ApiErrorMessage) response;
        if (state == WSState.CONNECTING) {
          waitForConnectionFuture.completeExceptionally(new WSException(error));
        }
        log.warn("{} Got API Error message: {}", logPrefix, error);
        callback.onErrorMessage(error);
      }
    } catch (Exception e) {
      log.error("{} WS message parsing failed. Response: {}", logPrefix, text, e);
      closeOnError(e);
    }
  }

  @Override
  public void onOpen(@NotNull final WebSocket ws, @NotNull final Response response) {
    super.onOpen(ws, response);
    log.info("{} WS opened.", logPrefix);
    lastReceivedTime = Instant.now();
    callback.onOpen(response);
  }

  @Override
  public void onClosed(@NotNull final WebSocket ws, final int code, @NotNull final String reason) {
    log.info("{} WS closed. Reason: {}, code: {}", logPrefix, reason, code);
    super.onClosed(ws, code, reason);
    state = WSState.IDLE;
    callback.onClosed(code, reason);
  }

  @Override
  public void onFailure(
      @NotNull final WebSocket ws, @NotNull final Throwable t, @Nullable final Response response) {
    super.onFailure(ws, t, response);
    if (state == WSState.CONNECTING) {
      // Should not normally happen, since the error msg should arrive first
      // Though, we need to cover this case due to its async nature
      log.error(
          "{} Failure during connecting. Response: {}.",
          logPrefix,
          extractResponseBody(response),
          t);
      waitForConnectionFuture.completeExceptionally(t);
      return;
    }

    if (state == WSState.IDLE) {
      return; // this is a handled websocket closure. No failure event should be created.
    }

    log.error(
        "{} [Connection error] WS failure. Response: {}. Trying to reconnect...",
        logPrefix,
        extractResponseBody(response),
        t);
    if (reconnect(config.getWsReconnectionAttempts())) {
      log.info("{} Reconnected successfully.", logPrefix);
    } else {
      log.warn(
          "{} [Connection error] Could not reconnect in {} attempts. Closing WS...",
          logPrefix,
          config.getWsReconnectionAttempts());
      closeOnError(t);
      callback.onFailure(t, response);
    }
  }

  private void waitForSubscriptionOrThrow() throws WSException {
    waitForConnectionFuture = new CompletableFuture<>();
    try {
      final WelcomeMessage msg =
          waitForConnectionFuture.get(config.getWsConnectionTimeoutSec(), TimeUnit.SECONDS);
      requireNonNull(msg);
    } catch (Exception e) {
      closeOnError(e);
      throw new WSException(logPrefix + " Subscription failed.", e);
    }
  }

  private void close(final int code, final String reason) {
    log.info("{} Closing WS.", logPrefix);
    if (nonNull(webSocket)) {
      webSocket.close(code, reason);
      webSocket = null;
    }

    if (nonNull(timer)) {
      timer.cancel();
    }
  }

  private void subscribe(final Set<Channel> subscriptions) throws WSException {
    final WSRequest req = new WSRequest(WSAction.SUBSCRIBE, subscriptions);
    sendRequest(req);
    waitForSubscriptionOrThrow();
  }

  protected void sendRequest(final WSRequest request) throws WSException {
    try {
      if (state == WSState.IDLE) {
        throw new IllegalStateException(
            logPrefix + " WebSocket is not ready to send requests. State: " + state);
      }

      final String requestStr = objectMapper.writeValueAsString(request);
      log.debug("{} Sending request {}", logPrefix, requestStr);

      if (!webSocket.send(requestStr)) {
        throw new RuntimeException(logPrefix + " Request was not send: " + requestStr);
      }
    } catch (Exception ex) {
      closeOnError(ex);
      throw new WSException(logPrefix + " Failed to send: " + request, ex);
    }
  }

  private void closeOnError(final Throwable ex) {
    log.warn(
        "{} [Connection error] Connection will be closed due to error: {}",
        logPrefix,
        ex.getMessage());
    state = WSState.IDLE;
    this.close(WS_CLOSE_ON_ERROR_CODE, ex.getClass().getSimpleName());
  }

  @SneakyThrows
  static String extractResponseBody(final Response response) {
    if (isNull(response)) {
      return null;
    }
    if (isNull(response.body())) {
      return null;
    }
    return response.body().string();
  }

  private static ObjectMapper configureObjectMapper() {
    return JsonMapper.builder()
        .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
        .build();
  }

  static Request buildRequestFromHost(final String host) {
    return new Request.Builder().url(host).build();
  }

  class HealthcheckTask extends TimerTask implements Runnable {
    @Override
    public void run() {
      log.debug("{} Start healthcheck...", logPrefix);
      final long lastUpdateAge = Duration.between(lastReceivedTime, Instant.now()).getSeconds();
      if (lastUpdateAge > WS_STALE_DETECTION_WINDOW_SEC) {
        log.warn(
            "{} Healthcheck failed, the connection is stale. No updates in {}sec",
            logPrefix,
            lastUpdateAge);
        if (!reconnect(config.getWsReconnectionAttempts())) {
          log.warn("{} Reconnection failed. Close the connection.", logPrefix);
          close();
        }
      }
    }
  }
}
