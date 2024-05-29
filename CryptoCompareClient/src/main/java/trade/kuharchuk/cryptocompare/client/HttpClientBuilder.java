package trade.kuharchuk.cryptocompare.client;

import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class HttpClientBuilder {
  private final CryptoCompareParams config;
  private final HttpLoggingInterceptor loggingInterceptor;

  public HttpClientBuilder(final CryptoCompareParams config) {
    this(config, getDefaultLoggingInterceptor());
  }

  public HttpClientBuilder(
      final CryptoCompareParams config, final HttpLoggingInterceptor loggingInterceptor) {
    this.config = config;
    this.loggingInterceptor = loggingInterceptor;
  }

  public OkHttpClient buildClient() {
    final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    clientBuilder
        .connectTimeout(this.config.getHttpConnectTimeout(), TimeUnit.MILLISECONDS)
        .readTimeout(this.config.getHttpReadTimeout(), TimeUnit.MILLISECONDS)
        .writeTimeout(this.config.getHttpWriteTimeout(), TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(this.config.isRetryOnConnectionFailure());

    if (isNotEmpty(config.getApiKey())) {
      final AuthenticationInterceptor signatureInterceptor =
          new AuthenticationInterceptor(config.getApiKey());
      clientBuilder.addInterceptor(signatureInterceptor);
    }

    if (this.config.isLogTraffic() && loggingInterceptor != null) {
      clientBuilder.addInterceptor(loggingInterceptor);
    }
    return clientBuilder.build();
  }

  @NotNull
  private static HttpLoggingInterceptor getDefaultLoggingInterceptor() {
    final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new TrafficLogger());
    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return logInterceptor;
  }
}
