package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.kuharchuk.cryptocompare.domain.InstrumentStatus;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentData {
  @JsonAlias("TYPE")
  private String type;
  @JsonAlias("INSTRUMENT_STATUS")
  private InstrumentStatus instrumentStatus;
  @JsonAlias("INSTRUMENT")
  private String instrument;
  @JsonAlias("HISTO_SHARD")
  private String histoShard;
  @JsonAlias("MAPPED_INSTRUMENT")
  private String mappedInstrument;
  @JsonAlias("INSTRUMENT_MAPPING")
  private InstrumentMapping instrumentMapping;
  @JsonAlias("HAS_TRADES_SPOT")
  private Boolean hasTradesSpot;
  @JsonAlias("FIRST_TRADE_SPOT_TIMESTAMP")
  private Long firstTradeSpotTimestamp;
  @JsonAlias("LAST_TRADE_SPOT_TIMESTAMP")
  private Long lastTradeSpotTimestamp;
  @JsonAlias("TOTAL_TRADES_SPOT")
  private Long totalTradesSpot;
}