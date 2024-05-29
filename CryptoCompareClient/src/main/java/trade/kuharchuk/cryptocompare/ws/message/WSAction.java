package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WSAction {
  SUBSCRIBE("SubAdd"),
  UNSUBSCRIBE("SubRemove");

  @Getter @JsonValue private final String actionName;
}
