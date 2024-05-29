package com.litesoftwares.coingecko.domain.Coins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinPriceData {
  @JsonAlias("usd")
  private BigDecimal usdPrice;
  @JsonAlias("usd_market_cap")
  private BigDecimal marketCap;
  @JsonAlias("usd_24h_vol")
  private BigDecimal usdVolume24H;
  @JsonAlias("usd_24h_change")
  private BigDecimal priceChange24H;
  @JsonAlias("last_updated_at")
  private long lastUpdated;
}
