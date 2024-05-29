package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import trade.kuharchuk.cryptocompare.ws.Channel;

import java.util.Set;

@Value
@AllArgsConstructor
public class WSRequest {
  WSAction action;
  @JsonProperty("subs")
  Set<Channel> channels;
}
