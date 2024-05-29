package trade.kuharchuk.cryptocompare.service;

import trade.kuharchuk.cryptocompare.exception.DataApiException;
import trade.kuharchuk.cryptocompare.response.AggregateInstrumentLatestTick;
import trade.kuharchuk.cryptocompare.response.HistoricalOHLCV;
import trade.kuharchuk.cryptocompare.response.InstrumentMetadataResponse;
import trade.kuharchuk.cryptocompare.service.endpoint.AggregatePriceApi;
import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import trade.kuharchuk.cryptocompare.client.CryptoCompareClient;
import trade.kuharchuk.cryptocompare.domain.ReferenceRateMarket;
import trade.kuharchuk.cryptocompare.request.HistoricalOHLCVRequest;
import trade.kuharchuk.cryptocompare.request.InstrumentMetadataFieldGroup;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AggregatedPriceDataService {
  private static final String INSTRUMENT_NOT_SUPPORTED_MSG = "Not found: instruments parameter";
  public static final ReferenceRateMarket DEFAULT_MARKET = ReferenceRateMarket.CADLI;
  public static final Set<InstrumentMetadataFieldGroup> BASIC_INSTRUMENT_DATA =
      EnumSet.of(InstrumentMetadataFieldGroup.STATUS);
  public static final int HISTORICAL_DATA_MAX_ITEMS = 2000;
  public static final int MAX_INSTRUMENTS_PER_REQUEST = 25;

  private final AggregatePriceApi api;
  private final CryptoCompareClient client;

  public AggregatedPriceDataService(final CryptoCompareParams config) {
    this.client = new CryptoCompareClient(config);
    this.api = client.createService(AggregatePriceApi.class);
  }

  public Map<String, InstrumentMetadataResponse> getAggregatedInstruments(
      final Set<String> instruments) {
    if (instruments.size() > MAX_INSTRUMENTS_PER_REQUEST) {
      throw new IllegalArgumentException(
          "Array is too long. Got "
              + instruments.size()
              + ", max - "
              + MAX_INSTRUMENTS_PER_REQUEST);
    }
    try {
      return client.executeSync(
          api.getInstrumentMetadata(
              DEFAULT_MARKET.getName(), instruments, BASIC_INSTRUMENT_DATA, false));
    } catch (DataApiException ex) {
      if (ex.getErrorMessage().contains(INSTRUMENT_NOT_SUPPORTED_MSG)) {
        return Map.of();
      }
      throw ex;
    }
  }

  public Map<String, AggregateInstrumentLatestTick> getLatestTick(
      final ReferenceRateMarket market,
      final List<String> instruments,
      final List<String> groups,
      final Boolean applyMapping) {
    return client.executeSync(
        api.getAggregatedLatestTick(market.getName(), instruments, groups, applyMapping));
  }

  public Map<String, AggregateInstrumentLatestTick> getLatestTick(final List<String> instruments) {
    return getLatestTick(DEFAULT_MARKET, instruments, null, null);
  }

  public List<HistoricalOHLCV> getPriceHistoryMinute(final HistoricalOHLCVRequest request) {
    return client.executeSync(
        api.getHistoricalMinutesOHLCV(
            request.getMarket().getName(),
            request.getInstrument(),
            request.getLimit(),
            request.getToTimestamp(),
            request.getGroups(),
            request.getAggregateSize(),
            request.getFill(),
            request.getApplyMapping(),
            request.getResponseFormat()));
  }

  public List<HistoricalOHLCV> getPriceHistoryHour(final HistoricalOHLCVRequest request) {
    return client.executeSync(
        api.getHistoricalHoursOHLCV(
            request.getMarket().getName(),
            request.getInstrument(),
            request.getLimit(),
            request.getToTimestamp(),
            request.getGroups(),
            request.getAggregateSize(),
            request.getFill(),
            request.getApplyMapping(),
            request.getResponseFormat()));
  }

  public List<HistoricalOHLCV> getPriceHistoryDay(final HistoricalOHLCVRequest request) {
    return client.executeSync(
        api.getHistoricalDailyOHLCV(
            request.getMarket().getName(),
            request.getInstrument(),
            request.getLimit(),
            request.getToTimestamp(),
            request.getGroups(),
            request.getAggregateSize(),
            request.getFill(),
            request.getApplyMapping(),
            request.getResponseFormat()));
  }
}
