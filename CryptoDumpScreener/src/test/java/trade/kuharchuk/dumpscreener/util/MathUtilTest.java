package trade.kuharchuk.dumpscreener.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class MathUtilTest {

  @Test
  void test_alternativeMoneyFormat() {
    Assertions.assertEquals("765.0", MathUtil.alternativeMoneyFormat(new BigDecimal(765)));
    Assertions.assertEquals("1.3k", MathUtil.alternativeMoneyFormat(new BigDecimal(1325)));
    Assertions.assertEquals("123.0k", MathUtil.alternativeMoneyFormat(new BigDecimal(123000)));
    Assertions.assertEquals("4.4M", MathUtil.alternativeMoneyFormat(new BigDecimal(4350000)));
    Assertions.assertEquals("4.4B", MathUtil.alternativeMoneyFormat(new BigDecimal("4350000000")));
  }
}