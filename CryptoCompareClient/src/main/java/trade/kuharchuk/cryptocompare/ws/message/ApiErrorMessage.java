package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorMessage extends BaseResponse {
  private String message;
  private String parameter;
  private String info;
}
