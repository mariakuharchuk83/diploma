package trade.kuharchuk.cryptocompare.client;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.AUTHENTICATION_HEADER;
import static trade.kuharchuk.cryptocompare.CryptoCompareClientConstant.AUTHENTICATION_HEADER_VALUE_PREFIX;

/**
 * A request interceptor that injects the API Key Header into requests and signs messages.
 */
@Slf4j
public class AuthenticationInterceptor implements Interceptor {
  private final String apiKey;

  public AuthenticationInterceptor(final String apiKey) {
    Objects.requireNonNull(apiKey, "API-key is absent");
    this.apiKey = apiKey;
  }

  public Response intercept(final Chain chain) throws IOException {
    Request origRequest = chain.request();

    final Request enrichedRequest =
        origRequest
            .newBuilder()
            .header(AUTHENTICATION_HEADER, AUTHENTICATION_HEADER_VALUE_PREFIX + this.apiKey)
            .build();
    return chain.proceed(enrichedRequest);
  }
}
