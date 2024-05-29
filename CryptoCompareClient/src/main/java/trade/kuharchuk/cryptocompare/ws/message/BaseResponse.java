package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type",
    visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = WelcomeMessage.class, name = "20"),
  @JsonSubTypes.Type(
      value = InfoMessage.class,
      names = {"3", "16", "17", "18"}),
  @JsonSubTypes.Type(value = Heartbeat.class, name = "999"),
  @JsonSubTypes.Type(value = AggregateIndexUpdate.class, name = "5"),
  @JsonSubTypes.Type(
      value = ApiErrorMessage.class,
      names = {"401", "429", "500"})
})
public abstract class BaseResponse {
  private MessageType type;
}
