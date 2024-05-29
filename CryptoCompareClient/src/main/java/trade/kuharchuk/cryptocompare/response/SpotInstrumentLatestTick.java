package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import trade.kuharchuk.cryptocompare.domain.PriceChangeDirection;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotInstrumentLatestTick {
  @JsonProperty("TYPE")
  private String type;

  @JsonProperty("MARKET")
  private String market;

  @JsonProperty("INSTRUMENT")
  private String instrument;

  @JsonProperty("MAPPED_INSTRUMENT")
  private String mappedInstrument;

  @JsonProperty("BASE")
  private String base;

  @JsonProperty("QUOTE")
  private String quote;

  @JsonProperty("BASE_ID")
  private int baseId;

  @JsonProperty("QUOTE_ID")
  private int quoteId;

  @JsonProperty("TRANSFORM_FUNCTION")
  private String transformFunction;

  @JsonProperty("CCSEQ")
  private long ccseq;

  @JsonProperty("PRICE")
  private BigDecimal price;

  @JsonProperty("PRICE_FLAG")
  private PriceChangeDirection priceFlag;

  @JsonProperty("PRICE_LAST_UPDATE_TS")
  private long priceLastUpdateTs;

  @JsonProperty("PRICE_LAST_UPDATE_TS_NS")
  private long priceLastUpdateTsNs;

  @JsonProperty("LAST_TRADE_QUANTITY")
  private BigDecimal lastTradeQuantity;

  @JsonProperty("LAST_TRADE_QUOTE_QUANTITY")
  private BigDecimal lastTradeQuoteQuantity;

  @JsonProperty("LAST_TRADE_ID")
  private String lastTradeId;

  @JsonProperty("LAST_TRADE_CCSEQ")
  private long lastTradeCcseq;

  @JsonProperty("LAST_TRADE_SIDE")
  private String lastTradeSide;

  @JsonProperty("LAST_PROCESSED_TRADE_TS")
  private long lastProcessedTradeTs;

  @JsonProperty("LAST_PROCESSED_TRADE_TS_NS")
  private long lastProcessedTradeTsNs;

  @JsonProperty("LAST_PROCESSED_TRADE_PRICE")
  private BigDecimal lastProcessedTradePrice;

  @JsonProperty("LAST_PROCESSED_TRADE_QUANTITY")
  private BigDecimal lastProcessedTradeQuantity;

  @JsonProperty("LAST_PROCESSED_TRADE_QUOTE_QUANTITY")
  private BigDecimal lastProcessedTradeQuoteQuantity;

  @JsonProperty("LAST_PROCESSED_TRADE_SIDE")
  private String lastProcessedTradeSide;

  @JsonProperty("LAST_PROCESSED_TRADE_CCSEQ")
  private long lastProcessedTradeCcseq;

  @JsonProperty("MOVING_7_DAY_CHANGE")
  private BigDecimal moving7DayChange;

  // Fields from AggregateInstrumentLatestTick.java
  @JsonProperty("MOVING_30_DAY_CHANGE")
  private BigDecimal moving30DayChange;

  @JsonProperty("MOVING_30_DAY_CHANGE_PERCENTAGE")
  private BigDecimal moving30DayChangePercentage;

  @JsonProperty("MOVING_90_DAY_VOLUME")
  private BigDecimal moving90DayVolume;

  @JsonProperty("MOVING_90_DAY_QUOTE_VOLUME")
  private BigDecimal moving90DayQuoteVolume;

  @JsonProperty("MOVING_90_DAY_VOLUME_TOP_TIER")
  private BigDecimal moving90DayVolumeTopTier;

  @JsonProperty("MOVING_90_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving90DayQuoteVolumeTopTier;

  @JsonProperty("MOVING_90_DAY_VOLUME_DIRECT")
  private BigDecimal moving90DayVolumeDirect;

  @JsonProperty("MOVING_90_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving90DayQuoteVolumeDirect;

  @JsonProperty("MOVING_90_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving90DayVolumeTopTierDirect;

  @JsonProperty("MOVING_90_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving90DayQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_90_DAY_OPEN")
  private BigDecimal moving90DayOpen;

  @JsonProperty("MOVING_90_DAY_HIGH")
  private BigDecimal moving90DayHigh;

  @JsonProperty("MOVING_90_DAY_LOW")
  private BigDecimal moving90DayLow;

  @JsonProperty("MOVING_90_DAY_TOTAL_INDEX_UPDATES")
  private int moving90DayTotalIndexUpdates;

  @JsonProperty("MOVING_90_DAY_CHANGE")
  private BigDecimal moving90DayChange;

  @JsonProperty("MOVING_90_DAY_CHANGE_PERCENTAGE")
  private BigDecimal moving90DayChangePercentage;

  @JsonProperty("MOVING_180_DAY_VOLUME")
  private BigDecimal moving180DayVolume;

  @JsonProperty("MOVING_180_DAY_QUOTE_VOLUME")
  private BigDecimal moving180DayQuoteVolume;

  @JsonProperty("MOVING_180_DAY_VOLUME_TOP_TIER")
  private BigDecimal moving180DayVolumeTopTier;

  @JsonProperty("MOVING_180_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving180DayQuoteVolumeTopTier;

  @JsonProperty("MOVING_180_DAY_VOLUME_DIRECT")
  private BigDecimal moving180DayVolumeDirect;

  @JsonProperty("MOVING_180_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving180DayQuoteVolumeDirect;

  @JsonProperty("MOVING_180_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving180DayVolumeTopTierDirect;

  @JsonProperty("MOVING_180_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving180DayQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_180_DAY_OPEN")
  private BigDecimal moving180DayOpen;

  @JsonProperty("MOVING_180_DAY_HIGH")
  private BigDecimal moving180DayHigh;

  @JsonProperty("MOVING_180_DAY_LOW")
  private BigDecimal moving180DayLow;

  @JsonProperty("MOVING_180_DAY_TOTAL_INDEX_UPDATES")
  private int moving180DayTotalIndexUpdates;

  @JsonProperty("MOVING_180_DAY_CHANGE")
  private BigDecimal moving180DayChange;

  @JsonProperty("MOVING_180_DAY_CHANGE_PERCENTAGE")
  private BigDecimal moving180DayChangePercentage;

  @JsonProperty("MOVING_365_DAY_VOLUME")
  private BigDecimal moving365DayVolume;

  @JsonProperty("MOVING_365_DAY_QUOTE_VOLUME")
  private BigDecimal moving365DayQuoteVolume;

  @JsonProperty("MOVING_365_DAY_VOLUME_TOP_TIER")
  private BigDecimal moving365DayVolumeTopTier;

  @JsonProperty("MOVING_365_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving365DayQuoteVolumeTopTier;

  @JsonProperty("MOVING_365_DAY_VOLUME_DIRECT")
  private BigDecimal moving365DayVolumeDirect;

  @JsonProperty("MOVING_365_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving365DayQuoteVolumeDirect;

  @JsonProperty("MOVING_365_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving365DayVolumeTopTierDirect;

  @JsonProperty("MOVING_365_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving365DayQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_365_DAY_OPEN")
  private BigDecimal moving365DayOpen;

  @JsonProperty("MOVING_365_DAY_HIGH")
  private BigDecimal moving365DayHigh;

  @JsonProperty("MOVING_365_DAY_LOW")
  private BigDecimal moving365DayLow;

  @JsonProperty("MOVING_365_DAY_TOTAL_INDEX_UPDATES")
  private int moving365DayTotalIndex;
}