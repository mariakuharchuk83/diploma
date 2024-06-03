package trade.kuharchuk.dumpscreener.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Test;
import trade.kuharchuk.dumpscreener.config.AppProperties;
import trade.kuharchuk.dumpscreener.domain.Token;
import trade.kuharchuk.dumpscreener.domain.TradePair;
import trade.kuharchuk.dumpscreener.enums.CentralizedExchange;
import trade.wayruha.cryptocompare.CryptoCompareParams;
import trade.wayruha.cryptocompare.client.CryptoCompareClient;
import trade.wayruha.cryptocompare.service.SpotDataService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CexServiceTest {

  CexService service = new CexService(spotDataService(getCryptoCompareClient(getProperties())), null);

  @Test
  void testGetPrice() {
    Map<CentralizedExchange, TradePair> pairs = Map.of(CentralizedExchange.BINANCE, new TradePair("BTC", "USDT"));
    final Token token = Token.builder().tradePairs(pairs).build();
    assertNotNull(service.getDollarPrice(token, CentralizedExchange.BINANCE));
  }

  private static AppProperties getProperties() {
    final AppProperties appProperties = new AppProperties();
    final AppProperties.CryptoCompare cryptoCompare = new AppProperties.CryptoCompare();
    appProperties.setCryptoCompare(cryptoCompare);
    cryptoCompare.setApiKey("0ad56d567c3724cce20251382c73a67384759e3726258c89efe489f392d0e52d");
    return appProperties;
  }

  public static SpotDataService spotDataService(CryptoCompareClient client) {
    return new SpotDataService(client);
  }

  public static CryptoCompareClient getCryptoCompareClient(AppProperties properties) {
    final CryptoCompareParams params = new CryptoCompareParams();
    params.setApiKey(properties.getCryptoCompare().getApiKey());

    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    final CryptoCompareClient client = new CryptoCompareClient(params, objectMapper);
    return client;
  }
}