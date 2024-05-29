package trade.kuharchuk.cryptocompare.service.endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import trade.kuharchuk.cryptocompare.response.AggregateInstrumentLatestTick;
import trade.kuharchuk.cryptocompare.response.DataApiResponse;
import trade.kuharchuk.cryptocompare.response.HistoricalOHLCV;
import trade.kuharchuk.cryptocompare.response.InstrumentMetadataResponse;
import trade.kuharchuk.cryptocompare.domain.ResponseFormat;
import trade.kuharchuk.cryptocompare.request.HistoricalDataParameterGroup;
import trade.kuharchuk.cryptocompare.request.InstrumentMetadataFieldGroup;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AggregatePriceApi {

  @GET("index/cc/v1/latest/tick")
  Call<DataApiResponse<Map<String, AggregateInstrumentLatestTick>>> getAggregatedLatestTick(
      @Query("market") String market,
      @Query("instruments") List<String> instruments,
      @Query("groups") List<String> groups,
      @Query("apply_mapping") Boolean applyMapping);

  @GET("/index/cc/v1/historical/days")
  Call<DataApiResponse<List<HistoricalOHLCV>>> getHistoricalDailyOHLCV(
      @Query("market") String market,
      @Query("instrument") String instrument,
      @Query("limit") Integer limit,
      @Query("to_ts") Long toTimestamp,
      @Query("groups") List<HistoricalDataParameterGroup> groups,
      @Query("aggregate") Integer aggregateSize,
      @Query("fill") Boolean fill,
      @Query("apply_mapping") Boolean applyMapping,
      @Query("response_format") ResponseFormat responseFormat);

  @GET("/index/cc/v1/historical/hours")
  Call<DataApiResponse<List<HistoricalOHLCV>>> getHistoricalHoursOHLCV(
      @Query("market") String market,
      @Query("instrument") String instrument,
      @Query("limit") Integer limit,
      @Query("to_ts") Long toTimestamp,
      @Query("groups") List<HistoricalDataParameterGroup> groups,
      @Query("aggregate") Integer aggregateSize,
      @Query("fill") Boolean fill,
      @Query("apply_mapping") Boolean applyMapping,
      @Query("response_format") ResponseFormat responseFormat);

  @GET("/index/cc/v1/historical/minutes")
  Call<DataApiResponse<List<HistoricalOHLCV>>> getHistoricalMinutesOHLCV(
      @Query("market") String market,
      @Query("instrument") String instrument,
      @Query("limit") Integer limit,
      @Query("to_ts") Long toTimestamp,
      @Query("groups") List<HistoricalDataParameterGroup> groups,
      @Query("aggregate") Integer aggregateSize,
      @Query("fill") Boolean fill,
      @Query("apply_mapping") Boolean applyMapping,
      @Query("response_format") ResponseFormat responseFormat);

  @GET("/index/cc/v1/latest/instrument/metadata")
  Call<DataApiResponse<Map<String, InstrumentMetadataResponse>>> getInstrumentMetadata(
      @Query("market") String market,
      @Query("instruments") Set<String> instruments,
      @Query("groups") Set<InstrumentMetadataFieldGroup> groups,
      @Query("applyMapping") Boolean applyMapping);
}
