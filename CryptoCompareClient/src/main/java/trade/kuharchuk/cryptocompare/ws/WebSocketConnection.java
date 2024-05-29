package trade.kuharchuk.cryptocompare.ws;

import trade.kuharchuk.cryptocompare.exception.WSException;
import trade.kuharchuk.cryptocompare.ws.message.BaseResponse;

import java.time.Instant;
import java.util.Set;

public interface WebSocketConnection<T extends BaseResponse> {
  void connect(Set<Channel> channels) throws WSException;

  void close();

  boolean reconnect(int retryCount);

  WSState getState();

  Set<Channel> getChannels();

  Instant getLastReceivedTime();
}
