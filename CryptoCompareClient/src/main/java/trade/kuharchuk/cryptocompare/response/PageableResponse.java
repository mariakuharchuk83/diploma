package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageableResponse<T> implements Serializable {
  @JsonAlias("STATS")
  private Stats stats;

  @JsonAlias("LIST")
  private List<T> list;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @Data
  public static class Stats implements Serializable {
    @JsonAlias("PAGE")
    private int page;

    @JsonAlias("PAGE_SIZE")
    private int pageSize;

    @JsonAlias("TOTAL_ASSETS")
    private int totalAssets;
  }
}
