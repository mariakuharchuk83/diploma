package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataApiError {
  private int type;
  private String message;

  @JsonAlias("other_info")
  private OtherInfo otherInfo;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OtherInfo {
    private String param;
    private List<String> values;

    @JsonAlias("calls_made")
    private RateLimit callsMade;

    @JsonAlias("max_calls")
    private RateLimit maxCalls;
  }
}
