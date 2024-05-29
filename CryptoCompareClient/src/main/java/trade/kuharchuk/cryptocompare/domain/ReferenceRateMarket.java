package trade.kuharchuk.cryptocompare.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReferenceRateMarket {
  CCXRP("ccxrp"),
  CADLI("cadli"),
  CCIX("ccix");

  private final String name;

  ReferenceRateMarket(final String name) {
    this.name = name;
  }

  @JsonValue
  public String getName() {
    return name;
  }
}
