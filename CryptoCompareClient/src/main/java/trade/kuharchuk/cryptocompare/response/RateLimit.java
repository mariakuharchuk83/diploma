package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateLimit {
  private Integer second;
  private Integer minute;
  private Integer hour;
  private Integer day;
  private Integer month;

  @JsonAlias("total_calls")
  private Integer totalCalls;
}
