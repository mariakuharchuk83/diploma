package trade.kuharchuk.cryptocompare.ws;

import lombok.experimental.UtilityClass;

import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
class IdGenerator {
  private static final AtomicInteger COUNTER = new AtomicInteger();

  static int getNextId() {
    return COUNTER.incrementAndGet();
  }
}
