package trade.kuharchuk.cryptocompare.ws;

import trade.kuharchuk.cryptocompare.ws.message.MessageType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Value;

import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@Value
@JsonSerialize(using = Channel.ChannelSerializer.class)
public class Channel {
  public static final String SUBSCRIPTION_WILDCARD = "*";

  private MessageType type;
  private List<String> parameters;

  private Channel(final MessageType type, final String... parameters) {
    this.type = type;
    this.parameters = List.of(parameters);
  }

  public static Channel aggregateIndexSubscription(
      final String index, final String base, final String quote) {
    return new Channel(
        MessageType.AGGREGATE_INDEX, optional(index), optional(base), optional(quote));
  }

  public static Channel exchangeTickerSubscription(
      final String exchange, final String base, final String quote) {
    return new Channel(MessageType.TICKER, optional(exchange), optional(base), optional(quote));
  }

  private static String optional(final String param) {
    if (isNull(param)) {
      return SUBSCRIPTION_WILDCARD;
    }
    return param;
  }

  static class ChannelSerializer extends JsonSerializer<Channel> {
    private static final String PARAMETER_SEPARATOR = "~";

    @Override
    public void serialize(
        final Channel channel, final JsonGenerator gen, final SerializerProvider sp)
        throws IOException {
      if (isNull(channel)) {
        gen.writeNull();
        return;
      }

      final int code = channel.getType().getCode();
      if (channel.getParameters().isEmpty()) {
        gen.writeString(String.valueOf(code));
        return;
      }

      final String params = String.join(PARAMETER_SEPARATOR, channel.getParameters());
      final String stringRepresentation = code + PARAMETER_SEPARATOR + params;
      gen.writeString(stringRepresentation);
    }
  }
}
