package trade.kuharchuk.cryptocompare.response;

import trade.kuharchuk.cryptocompare.domain.PriceChangeDirection;
import trade.kuharchuk.cryptocompare.domain.ReferenceRateMarket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateInstrumentLatestTick {
  @JsonProperty("TYPE")
  private String type;

  @JsonProperty("MARKET")
  private ReferenceRateMarket market;

  @JsonProperty("INSTRUMENT")
  private String instrument;

  @JsonProperty("CCSEQ")
  private long ccseq;

  @JsonProperty("VALUE")
  private BigDecimal lastPrice;

  @JsonProperty("VALUE_FLAG")
  private PriceChangeDirection changeType;

  @JsonProperty("VALUE_LAST_UPDATE_TS")
  private long valueLastUpdateTs;

  @JsonProperty("VALUE_LAST_UPDATE_TS_NS")
  private long valueLastUpdateTsNanosecondsPart;

  @JsonProperty("LAST_UPDATE_QUANTITY")
  private BigDecimal lastUpdateQuantity;

  @JsonProperty("LAST_UPDATE_QUOTE_QUANTITY")
  private BigDecimal lastUpdateQuoteQuantity;

  @JsonProperty("LAST_UPDATE_VOLUME_TOP_TIER")
  private int lastUpdateVolumeTopTier;

  @JsonProperty("LAST_UPDATE_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal lastUpdateQuoteVolumeTopTier;

  @JsonProperty("LAST_UPDATE_VOLUME_DIRECT")
  private BigDecimal lastUpdateVolumeDirect;

  @JsonProperty("LAST_UPDATE_QUOTE_VOLUME_DIRECT")
  private BigDecimal lastUpdateQuoteVolumeDirect;

  @JsonProperty("LAST_UPDATE_VOLUME_TOP_TIER_DIRECT")
  private int lastUpdateVolumeTopTierDirect;

  @JsonProperty("LAST_UPDATE_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal lastUpdateQuoteVolumeTopTierDirect;

  @JsonProperty("LAST_UPDATE_CCSEQ")
  private long lastUpdateCCSEQ;

  @JsonProperty("CURRENT_HOUR_VOLUME")
  private BigDecimal currentHourVolume;

  @JsonProperty("CURRENT_HOUR_QUOTE_VOLUME")
  private BigDecimal currentHourQuoteVolume;

  @JsonProperty("CURRENT_HOUR_VOLUME_TOP_TIER")
  private BigDecimal currentHourVolumeTopTier;

  @JsonProperty("CURRENT_HOUR_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal currentHourQuoteVolumeTopTier;

  @JsonProperty("CURRENT_HOUR_VOLUME_DIRECT")
  private BigDecimal currentHourVolumeDirect;

  @JsonProperty("CURRENT_HOUR_QUOTE_VOLUME_DIRECT")
  private BigDecimal currentHourQuoteVolumeDirect;

  @JsonProperty("CURRENT_HOUR_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentHourVolumeTopTierDirect;

  @JsonProperty("CURRENT_HOUR_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentHourQuoteVolumeTopTierDirect;

  @JsonProperty("CURRENT_HOUR_OPEN")
  private BigDecimal currentHourOpen;

  @JsonProperty("CURRENT_HOUR_HIGH")
  private BigDecimal currentHourHigh;

  @JsonProperty("CURRENT_HOUR_LOW")
  private BigDecimal currentHourLow;

  @JsonProperty("CURRENT_HOUR_TOTAL_INDEX_UPDATES")
  private int currentHourTotalIndexUpdates;

  @JsonProperty("CURRENT_HOUR_CHANGE")
  private BigDecimal currentHourChange;

  @JsonProperty("CURRENT_HOUR_CHANGE_PERCENTAGE")
  private BigDecimal currentHourChangePercentage;

  @JsonProperty("CURRENT_DAY_VOLUME")
  private BigDecimal currentDayVolume;

  @JsonProperty("CURRENT_DAY_QUOTE_VOLUME")
  private BigDecimal currentDayQuoteVolume;

  @JsonProperty("CURRENT_DAY_VOLUME_TOP_TIER")
  private BigDecimal currentDayVolumeTopTier;

  @JsonProperty("CURRENT_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal currentDayQuoteVolumeTopTier;

  @JsonProperty("CURRENT_DAY_VOLUME_DIRECT")
  private BigDecimal currentDayVolumeDirect;

  @JsonProperty("CURRENT_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal currentDayQuoteVolumeDirect;

  @JsonProperty("CURRENT_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentDayVolumeTopTierDirect;

  @JsonProperty("CURRENT_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentDayQuoteVolumeTopTierDirect;

  @JsonProperty("CURRENT_DAY_OPEN")
  private BigDecimal currentDayOpen;

  @JsonProperty("CURRENT_DAY_HIGH")
  private BigDecimal currentDayHigh;

  @JsonProperty("CURRENT_DAY_LOW")
  private BigDecimal currentDayLow;

  @JsonProperty("CURRENT_DAY_TOTAL_INDEX_UPDATES")
  private int currentDayTotalIndexUpdates;

  @JsonProperty("CURRENT_DAY_CHANGE")
  private BigDecimal currentDayChange;

  @JsonProperty("CURRENT_DAY_CHANGE_PERCENTAGE")
  private BigDecimal currentDayChangePercentage;

  @JsonProperty("CURRENT_WEEK_VOLUME")
  private BigDecimal currentWeekVolume;

  @JsonProperty("CURRENT_WEEK_QUOTE_VOLUME")
  private BigDecimal currentWeekQuoteVolume;

  @JsonProperty("CURRENT_WEEK_VOLUME_TOP_TIER")
  private BigDecimal currentWeekVolumeTopTier;

  @JsonProperty("CURRENT_WEEK_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal currentWeekQuoteVolumeTopTier;

  @JsonProperty("CURRENT_WEEK_VOLUME_DIRECT")
  private BigDecimal currentWeekVolumeDirect;

  @JsonProperty("CURRENT_WEEK_QUOTE_VOLUME_DIRECT")
  private BigDecimal currentWeekQuoteVolumeDirect;

  @JsonProperty("CURRENT_WEEK_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentWeekVolumeTopTierDirect;

  @JsonProperty("CURRENT_WEEK_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentWeekQuoteVolumeTopTierDirect;

  @JsonProperty("CURRENT_WEEK_OPEN")
  private BigDecimal currentWeekOpen;

  @JsonProperty("CURRENT_WEEK_HIGH")
  private BigDecimal currentWeekHigh;

  @JsonProperty("CURRENT_WEEK_LOW")
  private BigDecimal currentWeekLow;

  @JsonProperty("CURRENT_WEEK_TOTAL_INDEX_UPDATES")
  private int currentWeekTotalIndexUpdates;

  @JsonProperty("CURRENT_WEEK_CHANGE")
  private BigDecimal currentWeekChange;

  @JsonProperty("CURRENT_WEEK_CHANGE_PERCENTAGE")
  private BigDecimal currentWeekChangePercentage;

  @JsonProperty("CURRENT_MONTH_VOLUME")
  private BigDecimal currentMonthVolume;

  @JsonProperty("CURRENT_MONTH_QUOTE_VOLUME")
  private BigDecimal currentMonthQuoteVolume;

  @JsonProperty("CURRENT_MONTH_VOLUME_TOP_TIER")
  private BigDecimal currentMonthVolumeTopTier;

  @JsonProperty("CURRENT_MONTH_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal currentMonthQuoteVolumeTopTier;

  @JsonProperty("CURRENT_MONTH_VOLUME_DIRECT")
  private BigDecimal currentMonthVolumeDirect;

  @JsonProperty("CURRENT_MONTH_QUOTE_VOLUME_DIRECT")
  private BigDecimal currentMonthQuoteVolumeDirect;

  @JsonProperty("CURRENT_MONTH_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentMonthVolumeTopTierDirect;

  @JsonProperty("CURRENT_MONTH_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentMonthQuoteVolumeTopTierDirect;

  @JsonProperty("CURRENT_MONTH_OPEN")
  private BigDecimal currentMonthOpen;

  @JsonProperty("CURRENT_MONTH_HIGH")
  private BigDecimal currentMonthHigh;

  @JsonProperty("CURRENT_MONTH_LOW")
  private BigDecimal currentMonthLow;

  @JsonProperty("CURRENT_MONTH_TOTAL_INDEX_UPDATES")
  private int currentMonthTotalIndexUpdates;

  @JsonProperty("CURRENT_MONTH_CHANGE")
  private BigDecimal currentMonthChange;

  @JsonProperty("CURRENT_MONTH_CHANGE_PERCENTAGE")
  private BigDecimal currentMonthChangePercentage;

  @JsonProperty("CURRENT_YEAR_VOLUME")
  private BigDecimal currentYearVolume;

  @JsonProperty("CURRENT_YEAR_QUOTE_VOLUME")
  private BigDecimal currentYearQuoteVolume;

  @JsonProperty("CURRENT_YEAR_VOLUME_TOP_TIER")
  private BigDecimal currentYearVolumeTopTier;

  @JsonProperty("CURRENT_YEAR_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal currentYearQuoteVolumeTopTier;

  @JsonProperty("CURRENT_YEAR_VOLUME_DIRECT")
  private BigDecimal currentYearVolumeDirect;

  @JsonProperty("CURRENT_YEAR_QUOTE_VOLUME_DIRECT")
  private BigDecimal currentYearQuoteVolumeDirect;

  @JsonProperty("CURRENT_YEAR_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentYearVolumeTopTierDirect;

  @JsonProperty("CURRENT_YEAR_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal currentYearQuoteVolumeTopTierDirect;

  @JsonProperty("CURRENT_YEAR_OPEN")
  private BigDecimal currentYearOpen;

  @JsonProperty("CURRENT_YEAR_HIGH")
  private BigDecimal currentYearHigh;

  @JsonProperty("CURRENT_YEAR_LOW")
  private BigDecimal currentYearLow;

  @JsonProperty("CURRENT_YEAR_TOTAL_INDEX_UPDATES")
  private int currentYearTotalIndexUpdates;

  @JsonProperty("CURRENT_YEAR_CHANGE")
  private BigDecimal currentYearChange;

  @JsonProperty("CURRENT_YEAR_CHANGE_PERCENTAGE")
  private BigDecimal currentYearChangePercentage;

  @JsonProperty("MOVING_24_HOUR_VOLUME")
  private BigDecimal moving24HourVolume;

  @JsonProperty("MOVING_24_HOUR_QUOTE_VOLUME")
  private BigDecimal moving24HourQuoteVolume;

  @JsonProperty("MOVING_24_HOUR_VOLUME_TOP_TIER")
  private BigDecimal moving24HourVolumeTopTier;

  @JsonProperty("MOVING_24_HOUR_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving24HourQuoteVolumeTopTier;

  @JsonProperty("MOVING_24_HOUR_VOLUME_DIRECT")
  private BigDecimal moving24HourVolumeDirect;

  @JsonProperty("MOVING_24_HOUR_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving24HourQuoteVolumeDirect;

  @JsonProperty("MOVING_24_HOUR_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving24HourVolumeTopTierDirect;

  @JsonProperty("MOVING_24_HOUR_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving24HourQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_24_HOUR_OPEN")
  private BigDecimal moving24HourOpen;

  @JsonProperty("MOVING_24_HOUR_HIGH")
  private BigDecimal moving24HourHigh;

  @JsonProperty("MOVING_24_HOUR_LOW")
  private BigDecimal moving24HourLow;

  @JsonProperty("MOVING_24_HOUR_TOTAL_INDEX_UPDATES")
  private int moving24HourTotalIndexUpdates;

  @JsonProperty("MOVING_24_HOUR_CHANGE")
  private BigDecimal moving24HourChange;

  @JsonProperty("MOVING_24_HOUR_CHANGE_PERCENTAGE")
  private BigDecimal moving24HourChangePercentage;

  @JsonProperty("MOVING_7_DAY_VOLUME")
  private BigDecimal moving7DayVolume;

  @JsonProperty("MOVING_7_DAY_QUOTE_VOLUME")
  private BigDecimal moving7DayQuoteVolume;

  @JsonProperty("MOVING_7_DAY_VOLUME_TOP_TIER")
  private BigDecimal moving7DayVolumeTopTier;

  @JsonProperty("MOVING_7_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving7DayQuoteVolumeTopTier;

  @JsonProperty("MOVING_7_DAY_VOLUME_DIRECT")
  private BigDecimal moving7DayVolumeDirect;

  @JsonProperty("MOVING_7_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving7DayQuoteVolumeDirect;

  @JsonProperty("MOVING_7_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving7DayVolumeTopTierDirect;

  @JsonProperty("MOVING_7_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving7DayQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_7_DAY_OPEN")
  private BigDecimal moving7DayOpen;

  @JsonProperty("MOVING_7_DAY_HIGH")
  private BigDecimal moving7DayHigh;

  @JsonProperty("MOVING_7_DAY_LOW")
  private BigDecimal moving7DayLow;

  @JsonProperty("MOVING_7_DAY_TOTAL_INDEX_UPDATES")
  private int moving7DayTotalIndexUpdates;

  @JsonProperty("MOVING_7_DAY_CHANGE")
  private BigDecimal moving7DayChange;

  @JsonProperty("MOVING_7_DAY_CHANGE_PERCENTAGE")
  private BigDecimal moving7DayChangePercentage;

  @JsonProperty("MOVING_30_DAY_VOLUME")
  private BigDecimal moving30DayVolume;

  @JsonProperty("MOVING_30_DAY_QUOTE_VOLUME")
  private BigDecimal moving30DayQuoteVolume;

  @JsonProperty("MOVING_30_DAY_VOLUME_TOP_TIER")
  private BigDecimal moving30DayVolumeTopTier;

  @JsonProperty("MOVING_30_DAY_QUOTE_VOLUME_TOP_TIER")
  private BigDecimal moving30DayQuoteVolumeTopTier;

  @JsonProperty("MOVING_30_DAY_VOLUME_DIRECT")
  private BigDecimal moving30DayVolumeDirect;

  @JsonProperty("MOVING_30_DAY_QUOTE_VOLUME_DIRECT")
  private BigDecimal moving30DayQuoteVolumeDirect;

  @JsonProperty("MOVING_30_DAY_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving30DayVolumeTopTierDirect;

  @JsonProperty("MOVING_30_DAY_QUOTE_VOLUME_TOP_TIER_DIRECT")
  private BigDecimal moving30DayQuoteVolumeTopTierDirect;

  @JsonProperty("MOVING_30_DAY_OPEN")
  private BigDecimal moving30DayOpen;

  @JsonProperty("MOVING_30_DAY_HIGH")
  private BigDecimal moving30DayHigh;

  @JsonProperty("MOVING_30_DAY_LOW")
  private BigDecimal moving30DayLow;

  @JsonProperty("MOVING_30_DAY_TOTAL_INDEX_UPDATES")
  private int moving30DayTotalIndexUpdates;

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
