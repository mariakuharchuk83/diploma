package trade.kuharchuk.cryptocompare.exception;

import trade.kuharchuk.cryptocompare.response.DataApiError;
import lombok.Getter;
import lombok.ToString;

import java.net.URL;

@Getter
@ToString
public class DataApiException extends RuntimeException {
  private final URL url;
  private final Integer httpCode;
  private final String errorMessage;
  private final DataApiError apiError;

  public DataApiException(final URL url, final int httpCode, final String errorMessage) {
    super(errorMessage);
    this.url = url;
    this.httpCode = httpCode;
    this.errorMessage = errorMessage;
    this.apiError = null;
  }

  public DataApiException(
      final URL url, final int httpCode, final String errorMessage, final DataApiError apiError) {
    super(errorMessage);
    this.url = url;
    this.httpCode = httpCode;
    this.errorMessage = errorMessage;
    this.apiError = apiError;
  }

  public DataApiException(final URL url, final String message, final Throwable cause) {
    super(message, cause);
    this.url = url;
    this.httpCode = null;
    this.errorMessage = null;
    this.apiError = null;
  }
}
