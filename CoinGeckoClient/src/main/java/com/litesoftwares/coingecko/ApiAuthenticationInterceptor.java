package com.litesoftwares.coingecko;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * A request interceptor that injects the API Key Header into requests
 */
public class ApiAuthenticationInterceptor implements Interceptor {
  private static final String AUTHENTICATION_HEADER_DEMO_KEY = "x-cg-demo-api-key";
  private static final String AUTHENTICATION_HEADER_PRO_KEY = "x-cg-pro-api-key";
  private final ApiKey apiKey;
  private final String authHeader;

  public ApiAuthenticationInterceptor(final ApiKey apiKey) {
    Objects.requireNonNull(apiKey, "API-key is absent");
    this.apiKey = apiKey;
    this.authHeader = apiKey.isProKey() ? AUTHENTICATION_HEADER_PRO_KEY : AUTHENTICATION_HEADER_DEMO_KEY;
  }

  public Response intercept(final Chain chain) throws IOException {
    Request origRequest = chain.request();

    final Request enrichedRequest =
        origRequest
            .newBuilder()
            .header(authHeader, this.apiKey.getApiKey())
            .build();
    return chain.proceed(enrichedRequest);
  }
}
