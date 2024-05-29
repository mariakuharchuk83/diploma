package trade.kuharchuk.cryptocompare.exception;

import trade.kuharchuk.cryptocompare.response.DataApiError;
import trade.kuharchuk.cryptocompare.response.RateLimit;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class RateLimitException extends DataApiException {
  private RateLimit callsMade;
  private RateLimit thresholds;

  public RateLimitException(final URL uri, final int httpCode, final DataApiError error) {
    super(uri, httpCode, error.getMessage(), error);
  }
}
