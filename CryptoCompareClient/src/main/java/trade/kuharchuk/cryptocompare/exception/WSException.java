package trade.kuharchuk.cryptocompare.exception;

import trade.kuharchuk.cryptocompare.ws.message.ApiErrorMessage;

public class WSException extends Exception {

  public WSException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public WSException(final ApiErrorMessage apiError) {
    super(apiError.toString());
  }
}
