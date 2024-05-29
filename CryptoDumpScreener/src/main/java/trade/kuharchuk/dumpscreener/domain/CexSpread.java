package trade.kuharchuk.dumpscreener.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import trade.kuharchuk.dumpscreener.enums.CentralizedExchange;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CexSpread {
  private CentralizedExchange sellExchange;
  private BigDecimal currentPrice;
  private BigDecimal spreadPercentage;
}
