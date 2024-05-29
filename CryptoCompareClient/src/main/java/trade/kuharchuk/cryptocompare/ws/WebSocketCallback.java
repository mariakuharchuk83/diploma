package trade.kuharchuk.cryptocompare.ws;

import trade.kuharchuk.cryptocompare.ws.message.ApiErrorMessage;
import okhttp3.Response;

public interface WebSocketCallback<T> {

  void onResponse(T response);

  void onErrorMessage(ApiErrorMessage error);

  default void onFailure(final Throwable ex, final Response response) {}

  default void onClosed(final int code, final String reason) {}

  default void onOpen(final Response response) {}
}
