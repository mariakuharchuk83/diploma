package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateIndexUpdate extends BaseResponse {
  private String market;

  @JsonAlias("fromSymbol")
  private String baseAsset;

  @JsonAlias("toSymbol")
  private String quoteAsset;

  private String flags;
  private long lastUpdate;
  private String lastTradeId;
  private BigDecimal price;
  private BigDecimal median;
  private BigDecimal lastVolume;
  private BigDecimal lastVolumeTo;
  private BigDecimal volumeDay;
  private BigDecimal volumeDayTo;
  private BigDecimal volume24Hour;
  private BigDecimal volume24HourTo;
  private BigDecimal openDay;
  private BigDecimal highDay;
  private BigDecimal lowDay;
  private BigDecimal open24Hour;
  private BigDecimal high24Hour;
  private BigDecimal low24Hour;
  private BigDecimal volumeHour;
  private BigDecimal volumeHourTo;
  private BigDecimal openHour;
  private BigDecimal highHour;
  private BigDecimal lowHour;
  private BigDecimal topTierVolume24Hour;
  private BigDecimal topTierVolume24HourTo;
  private String lastMarket;
}
