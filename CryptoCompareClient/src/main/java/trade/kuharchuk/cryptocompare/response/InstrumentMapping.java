package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentMapping {
  @JsonAlias("MAPPED_INSTRUMENT")
  private String mappedInstrument;
  @JsonAlias("BASE")
  private String base;
  @JsonAlias("BASE_ID")
  private Integer baseId;
  @JsonAlias("QUOTE")
  private String quote;
  @JsonAlias("QUOTE_ID")
  private Integer quoteId;
  @JsonAlias("TRANSFORM_FUNCTION")
  private String transformFunction;
  @JsonAlias("CREATED_ON")
  private Long createdOn;
}
