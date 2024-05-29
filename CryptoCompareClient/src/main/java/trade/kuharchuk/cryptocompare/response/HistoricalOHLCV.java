package trade.kuharchuk.cryptocompare.response;

import trade.kuharchuk.cryptocompare.domain.ReferenceRateMarket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalOHLCV {

  @JsonProperty("UNIT")
  private Timeframe timeframe;

  @JsonProperty("TIMESTAMP")
  private long timestamp;

  @JsonProperty("TYPE")
  private String type;

  @JsonProperty("MARKET")
  private ReferenceRateMarket referenceRateMarket;

  @JsonProperty("INSTRUMENT")
  private String instrument;

  @JsonProperty("OPEN")
  private BigDecimal open;

  @JsonProperty("HIGH")
  private BigDecimal high;

  @JsonProperty("LOW")
  private BigDecimal low;

  @JsonProperty("CLOSE")
  private BigDecimal close;

  @JsonProperty("VOLUME")
  private BigDecimal volume;

  @JsonProperty("QUOTE_VOLUME")
  private BigDecimal quoteVolume;

  @JsonProperty("FIRST_MESSAGE_TIMESTAMP")
  private long firstMessageTimestamp;

  @JsonProperty("LAST_MESSAGE_TIMESTAMP")
  private long lastMessageTimestamp;

  @JsonProperty("FIRST_MESSAGE_VALUE")
  private BigDecimal firstMessageValue;

  @JsonProperty("HIGH_MESSAGE_VALUE")
  private BigDecimal highMessageValue;

  @JsonProperty("HIGH_MESSAGE_TIMESTAMP")
  private long highMessageTimestamp;

  @JsonProperty("LOW_MESSAGE_VALUE")
  private BigDecimal lowMessageValue;

  @JsonProperty("LOW_MESSAGE_TIMESTAMP")
  private long lowMessageTimestamp;

  @JsonProperty("LAST_MESSAGE_VALUE")
  private BigDecimal lastMessageValue;

  @JsonProperty("TOTAL_INDEX_UPDATES")
  private long totalIndexUpdates;

  @JsonProperty("VOLUME_TOP_TIER")
  private BigDecimal volumeTopTier;

  @JsonProperty("QUOTE_VOLUME_TOP_TIER")
  private BigDecimal quoteVolumeTopTier;

  @JsonProperty("VOLUME_DIRECT")
  private BigDecimal volumeDirect;

  @JsonProperty("QUOTE_VOLUME_DIRECT")
  private BigDecimal quoteVolumeDirect;

  @JsonProperty("VOLUME_TOP_TIER_DIRECT")
  private BigDecimal volumeTopTierDirect;

  @JsonProperty("QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal quoteVolumeTopTierDirect;

  public enum Timeframe {
    MINUTE,
    HOUR,
    DAY
  }
}
