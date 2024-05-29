package trade.kuharchuk.cryptocompare;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class CryptoCompareClientConstant {
  public static final String DATA_API_BASE_URL = "https://data-api.cryptocompare.com";

  /** The default timeout is 30 seconds. */
  public static final long HTTP_CLIENT_TIMEOUT_MS = 30 * 1000;

  public static final int RATE_LIMIT_RESPONSE_CODE = 429;

  public static final String AUTHENTICATION_HEADER = "Authorization";
  public static final String AUTHENTICATION_HEADER_VALUE_PREFIX = "Apikey ";

  // WebSocket
  public static final String WS_BASE_URL = "wss://streamer.cryptocompare.com/v2";
  public static final int WS_HEALTHCHECK_INTERVAL_SEC = 30;
  public static final int WS_CONNECTION_TIMEOUT_SEC = 20;

  public static final int WS_RECONNECTION_DELAY_MS = 5000;
  public static final int WS_CLOSE_ON_ERROR_CODE = 4999;
  public static final int WS_CLOSE_GRACEFULLY_CODE = 1000;
  public static final String WS_CLOSE_GRACEFULLY_MSG = "closed gracefully";
  public static final String DEFAULT_WS_AGGREGATE_TICK_INDEX = "CCCAGG";

  public static final int WS_MAX_RECONNECT_ATTEMPTS = 3;
  public static final int WS_STALE_DETECTION_WINDOW_SEC = 60;
}
