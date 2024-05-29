package trade.kuharchuk.oneinch.service;

import trade.kuharchuk.oneinch.Chain;
import trade.kuharchuk.oneinch.ClientConstant;
import trade.kuharchuk.oneinch.OneInchParams;
import trade.kuharchuk.oneinch.client.InchApiClient;
import trade.kuharchuk.oneinch.request.SpotPricesRequest;
import trade.kuharchuk.oneinch.service.api.SpotApi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SpotService {
  private final SpotApi api;
  private final InchApiClient client;

  public SpotService(OneInchParams params) {
    this.client = new InchApiClient(params);
    this.api = client.createService(SpotApi.class);
  }

  public Map<String, BigDecimal> getTokenPrices(Chain chain, List<String> tokenAddresses, String currency) {
    final SpotPricesRequest payload = new SpotPricesRequest(tokenAddresses, currency);
    return client.executeSync(api.getTokenPrices(chain.getChainId(), payload));
  }

  public Map<String, BigDecimal> getTokenDollarPrices(Chain chain, List<String> tokenAddresses) {
    return getTokenPrices(chain, tokenAddresses, ClientConstant.USD_CURRENCY);
  }
}
