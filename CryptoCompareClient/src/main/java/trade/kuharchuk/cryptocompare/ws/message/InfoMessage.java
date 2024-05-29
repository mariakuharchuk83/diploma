package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(callSuper = true)
public class InfoMessage extends BaseResponse {
  private String message;
  private String info;

  @JsonAlias("SUB")
  private String subscription;
}
