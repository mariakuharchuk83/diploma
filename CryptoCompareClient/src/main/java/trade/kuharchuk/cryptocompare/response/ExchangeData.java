package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import trade.kuharchuk.cryptocompare.domain.InstrumentStatus;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeData {
  @JsonAlias("TYPE")
  private String type;
  @JsonAlias("EXCHANGE_STATUS")
  private InstrumentStatus exchangeStatus;
  @JsonAlias("MAPPED_INSTRUMENTS_TOTAL")
  private Integer mappedInstrumentsTotal;
  @JsonAlias("UNMAPPED_INSTRUMENTS_TOTAL")
  private Integer unmappedInstrumentsTotal;
  @JsonAlias("INSTRUMENT_STATUS")
  private Map<String, Object> instrumentStatus;
  @JsonAlias("TOTAL_TRADES_SPOT")
  private Long totalTradesSpot;
  @JsonAlias("HAS_ORDERBOOK_L2_MINUTE_SNAPSHOTS_ENABLED")
  private Boolean hasOrderbookL2MinuteSnapshotsEnabled;
  private Map<String, InstrumentData> instruments;
}
