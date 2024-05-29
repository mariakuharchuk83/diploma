package trade.kuharchuk.cryptocompare.request;

import trade.kuharchuk.cryptocompare.domain.ReferenceRateMarket;
import trade.kuharchuk.cryptocompare.domain.ResponseFormat;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public final class HistoricalOHLCVRequest {
  private final ReferenceRateMarket market;
  private final String instrument;
  private Integer limit;
  private Long toTimestamp;
  private List<HistoricalDataParameterGroup> groups;
  private Integer aggregateSize;
  private Boolean fill;
  private Boolean applyMapping;
  private ResponseFormat responseFormat;

  private HistoricalOHLCVRequest(final Builder builder) {
    this.market = Objects.requireNonNull(builder.market, "Market cannot be null");
    this.instrument = Objects.requireNonNull(builder.instrument, "Instrument cannot be null");
    this.limit = builder.limit;
    this.toTimestamp = builder.toTimestamp;
    this.groups = builder.groups;
    this.aggregateSize = builder.aggregateSize;
    this.fill = builder.fill;
    this.applyMapping = builder.applyMapping;
    this.responseFormat = builder.responseFormat;
  }

  public static Builder builder(final ReferenceRateMarket market, final String instrument) {
    return new Builder(market, instrument);
  }

  public static final class Builder {
    private final ReferenceRateMarket market;
    private final String instrument;
    private Integer limit;
    private Long toTimestamp;
    private List<HistoricalDataParameterGroup> groups;
    private Integer aggregateSize;
    private Boolean fill;
    private Boolean applyMapping;
    private ResponseFormat responseFormat;

    private Builder(final ReferenceRateMarket market, final String instrument) {
      this.market = market;
      this.instrument = instrument;
    }

    public HistoricalOHLCVRequest build() {
      return new HistoricalOHLCVRequest(this);
    }

    public Builder limit(final Integer limit) {
      this.limit = limit;
      return this;
    }

    public Builder toTimestamp(final Long toTimestamp) {
      this.toTimestamp = toTimestamp;
      return this;
    }

    public Builder groups(final List<HistoricalDataParameterGroup> groups) {
      this.groups = groups;
      return this;
    }

    public Builder aggregateSize(final Integer aggregateSize) {
      this.aggregateSize = aggregateSize;
      return this;
    }

    public Builder fill(final Boolean fill) {
      this.fill = fill;
      return this;
    }

    public Builder applyMapping(final Boolean applyMapping) {
      this.applyMapping = applyMapping;
      return this;
    }

    public Builder responseFormat(final ResponseFormat responseFormat) {
      this.responseFormat = responseFormat;
      return this;
    }
  }
}
