package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class DataApiResponse<T> {
  @JsonAlias("Data")
  private T data;

  @JsonAlias("Err")
  private DataApiError error;
}
