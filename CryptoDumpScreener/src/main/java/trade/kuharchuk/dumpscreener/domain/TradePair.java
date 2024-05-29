package trade.kuharchuk.dumpscreener.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import static trade.kuharchuk.dumpscreener.config.GlobalConstants.TRADE_PAIR_DIVIDER;

@Data
@AllArgsConstructor
public class TradePair {
  private String base;
  private String quote;

  public String format() {
    return base.toUpperCase() + TRADE_PAIR_DIVIDER + quote.toUpperCase();
  }

  @Override
  public String toString() {
    return format();
  }
}
