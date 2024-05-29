package trade.kuharchuk.cryptocompare;

import lombok.Data;

@Data
public class CryptoCompareParams {

  private String apiKey;

  private long httpConnectTimeout = CryptoCompareClientConstant.HTTP_CLIENT_TIMEOUT_MS;

  private long httpReadTimeout = CryptoCompareClientConstant.HTTP_CLIENT_TIMEOUT_MS;

  private long httpWriteTimeout = CryptoCompareClientConstant.HTTP_CLIENT_TIMEOUT_MS;

  /** Retry on failed connection, default true. */
  private boolean retryOnConnectionFailure = true;

  /** Should we log request's data? */
  private boolean logTraffic = false;

  private int wsReconnectionAttempts = CryptoCompareClientConstant.WS_MAX_RECONNECT_ATTEMPTS;

  private int wsHealthcheckIntervalSec = CryptoCompareClientConstant.WS_HEALTHCHECK_INTERVAL_SEC;

  private int wsConnectionTimeoutSec = CryptoCompareClientConstant.WS_CONNECTION_TIMEOUT_SEC;

  private int wsStaleDetectionWindowSec = CryptoCompareClientConstant.WS_STALE_DETECTION_WINDOW_SEC;
}
