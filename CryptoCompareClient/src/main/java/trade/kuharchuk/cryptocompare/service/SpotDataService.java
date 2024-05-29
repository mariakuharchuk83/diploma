package trade.kuharchuk.cryptocompare.service;

import trade.kuharchuk.cryptocompare.response.ExchangeData;
import trade.kuharchuk.cryptocompare.response.SpotInstrumentLatestTick;
import trade.kuharchuk.cryptocompare.service.endpoint.SpotApi;
import trade.kuharchuk.cryptocompare.client.CryptoCompareClient;
import trade.kuharchuk.cryptocompare.domain.InstrumentStatus;
import trade.kuharchuk.cryptocompare.request.PriceDataParameterGroup;

import java.util.List;
import java.util.Map;

import static trade.kuharchuk.cryptocompare.request.PriceDataParameterGroup.ID;
import static trade.kuharchuk.cryptocompare.request.PriceDataParameterGroup.VALUE;

public class SpotDataService {
  private static final List<PriceDataParameterGroup> BASIC_PRICE_DATA_FIELDS = List.of(ID, VALUE);

  private final SpotApi api;
  private final CryptoCompareClient client;

  public SpotDataService(CryptoCompareClient client) {
    this.api = client.createService(SpotApi.class);
    this.client = client;
  }

  public Map<String, ExchangeData> getAvailableMarkets(final String exchange, final List<String> instruments) {
    return client.executeSync(api.getMarketInstruments(exchange, instruments, InstrumentStatus.ACTIVE));
  }

  public Map<String, SpotInstrumentLatestTick> getLatestTick(final String exchange, final List<String> instruments) {
    return client.executeSync(api.getLatestTick(exchange, instruments, true, BASIC_PRICE_DATA_FIELDS));
  }
}
