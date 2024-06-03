package trade.kuharchuk.cryptocompare.client;

import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrafficLogger implements HttpLoggingInterceptor.Logger {
  public static final String CLASSNAME = TrafficLogger.class.getName();
  public static final String WS_LOGGER_SUFFIX = ".WebSocket";
  public static final String HTTP_LOGGER_SUFFIX = ".Http";
  public static final Logger WS_TRAFFIC_LOGGER =
      LoggerFactory.getLogger(CLASSNAME + WS_LOGGER_SUFFIX);
  public static final Logger HTTP_LOGGER = LoggerFactory.getLogger(CLASSNAME + HTTP_LOGGER_SUFFIX);

  public TrafficLogger() {}

  @Override
  public void log(@NotNull final String msg) {
    HTTP_LOGGER.trace(msg);
  }
}
