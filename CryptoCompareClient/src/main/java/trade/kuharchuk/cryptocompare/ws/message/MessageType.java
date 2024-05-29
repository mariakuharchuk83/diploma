package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public enum MessageType {
  // subscriptions
  TRADE(0),
  TICKER(2),
  AGGREGATE_INDEX(5),
  ORDER_BOOK_L2(8),
  FULL_VOLUME(11),
  FULL_TOP_TIER_VOLUME(21),
  OHLC_CANDLES(24),
  TOP_OF_ORDER_BOOK(30),

  // info-messages
  STREAMER_WELCOME(20),
  SUBSCRIBE_COMPLETE(16),
  LOAD_COMPLETE(3),
  UNSUBSCRIBE_COMPLETE(17),
  UNSUBSCRIBE_ALL_COMPLETE(18),
  HEARTBEAT(999),

  // errors
  UNAUTHORIZED(401),
  RATE_LIMIT_ERROR(429),
  PROCESSING_ERROR(500);

  @Getter @JsonValue private final int code;

  public static final Set<MessageType> ERROR_TYPES =
      EnumSet.of(UNAUTHORIZED, RATE_LIMIT_ERROR, PROCESSING_ERROR);

  MessageType(final int code) {
    this.code = code;
  }

  public static MessageType parse(final int code) {
    return Arrays.stream(values())
        .filter(v -> v.getCode() == code)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unrecognized code: " + code));
  }
}
