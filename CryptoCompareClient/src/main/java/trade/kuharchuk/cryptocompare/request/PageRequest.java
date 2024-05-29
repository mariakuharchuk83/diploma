package trade.kuharchuk.cryptocompare.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PageRequest implements Serializable {
  private Integer page;
  private Integer pageSize;

  public PageRequest(Integer page, Integer pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  public static PageRequest unpaged() {
    return new PageRequest(null, null);
  }

  public boolean isPaged() {
    return page != null && pageSize != null;
  }

  @JsonIgnore
  public int getOffset() {
    if (!isPaged()) return 0;
    return page * pageSize;
  }
}
