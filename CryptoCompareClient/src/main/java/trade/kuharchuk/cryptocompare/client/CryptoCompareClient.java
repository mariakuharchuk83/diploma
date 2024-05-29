package trade.kuharchuk.cryptocompare.client;

import trade.kuharchuk.cryptocompare.exception.DataApiException;
import trade.kuharchuk.cryptocompare.exception.RateLimitException;
import trade.kuharchuk.cryptocompare.response.DataApiError;
import trade.kuharchuk.cryptocompare.response.DataApiResponse;
import trade.kuharchuk.cryptocompare.response.RateLimit;
import trade.kuharchuk.cryptocompare.CryptoCompareClientConstant;
import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
public class CryptoCompareClient extends BaseApiClient {
  public CryptoCompareClient(final CryptoCompareParams config) {
    this(config, new ObjectMapper());
  }

  public CryptoCompareClient(final CryptoCompareParams config, final ObjectMapper mapper) {
    super(CryptoCompareClientConstant.DATA_API_BASE_URL, config, mapper);
  }

  public <T> T executeSync(final Call<DataApiResponse<T>> call) {
    final String rawRequestData = call.request().toString();
    final URL url = call.request().url().url();
    try {

      final Response<DataApiResponse<T>> response = call.execute();
      final int responseCode = response.code();
      final DataApiResponse<T> body = response.body();
      if (!response.isSuccessful()) {
        log.error(
            "Request failed: {}. {}. Message: {} ",
            responseCode,
            rawRequestData,
            response.message());
        final DataApiError apiError = parseError(response.errorBody());
        if (apiError != null) {
          if (responseCode == CryptoCompareClientConstant.RATE_LIMIT_RESPONSE_CODE) {
            throw parseRateLimitException(url, response, apiError);
          } else {
            throw new DataApiException(url, responseCode, apiError.getMessage(), apiError);
          }
        }
        throw new DataApiException(url, responseCode, response.message());
      }

      return body.getData();
    } catch (IOException e) {
      log.error("Unexpected error. Request data: {}", rawRequestData, e);
      throw new DataApiException(url, e.getMessage(), e);
    }
  }

  private DataApiError parseError(final ResponseBody errorBody) throws IOException {
    if (isNull(errorBody)) {
      return null;
    }
    final String json = errorBody.string();
    if (isBlank(json)) {
      return null;
    }
    final DataApiResponse dataApiResponse =
        getObjectMapper().readValue(json, DataApiResponse.class);
    return dataApiResponse != null ? dataApiResponse.getError() : null;
  }

  private static <T> RateLimitException parseRateLimitException(
      final URL url, final Response<DataApiResponse<T>> response, final DataApiError error) {
    final RateLimitException rateLimitException =
        new RateLimitException(url, response.code(), error);
    final RateLimit callsMade = error.getOtherInfo().getCallsMade();
    final RateLimit callThresholds = error.getOtherInfo().getMaxCalls();
    rateLimitException.setCallsMade(callsMade);
    rateLimitException.setThresholds(callThresholds);
    return rateLimitException;
  }
}
