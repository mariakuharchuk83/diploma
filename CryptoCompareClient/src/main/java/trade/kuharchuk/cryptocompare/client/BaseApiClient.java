package trade.kuharchuk.cryptocompare.client;

import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public abstract class BaseApiClient {
  private final Retrofit retrofit;
  @Getter private final CryptoCompareParams config;

  @Getter(AccessLevel.PROTECTED)
  private final OkHttpClient httpClient;

  @Getter(AccessLevel.PROTECTED)
  private final ObjectMapper objectMapper;

  public BaseApiClient(
      final String baseUrl, final CryptoCompareParams config, final ObjectMapper mapper) {
    this(baseUrl, config, mapper, new HttpClientBuilder(config), new RetrofitBuilder(mapper));
  }

  public BaseApiClient(
      final String baseUrl,
      final CryptoCompareParams config,
      final ObjectMapper objectMapper,
      final HttpClientBuilder httpClientBuilder,
      final RetrofitBuilder retrofitBuilder) {
    this.config = config;
    this.httpClient = httpClientBuilder.buildClient();
    this.objectMapper = objectMapper;
    this.retrofit = retrofitBuilder.buildRetrofit(httpClient, baseUrl);
  }

  public <T> T createService(final Class<T> service) {
    return this.retrofit.create(service);
  }
}
