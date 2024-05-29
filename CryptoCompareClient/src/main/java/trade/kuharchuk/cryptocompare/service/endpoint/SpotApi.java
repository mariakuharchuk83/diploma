package trade.kuharchuk.cryptocompare.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import trade.kuharchuk.cryptocompare.response.DataApiResponse;
import trade.kuharchuk.cryptocompare.response.ExchangeData;
import trade.kuharchuk.cryptocompare.response.SpotInstrumentLatestTick;
import trade.kuharchuk.cryptocompare.domain.InstrumentStatus;
import trade.kuharchuk.cryptocompare.request.PriceDataParameterGroup;

import java.util.List;
import java.util.Map;

public interface SpotApi {

  @GET("spot/v1/markets/instruments")
  Call<DataApiResponse<Map<String, ExchangeData>>> getMarketInstruments(
      @Query("market") String market,
      @Query("instruments") List<String> instruments,
      @Query("instrument_status") InstrumentStatus instrumentStatus);

  @GET("spot/v1/latest/tick")
  Call<DataApiResponse<Map<String, SpotInstrumentLatestTick>>> getLatestTick(
      @Query("market") String market,
      @Query("instruments") List<String> instruments,
      @Query("apply_mapping") Boolean applyMapping,
      @Query("groups") List<PriceDataParameterGroup> groups);
}
