package trade.kuharchuk.cryptocompare.request;

import trade.kuharchuk.cryptocompare.domain.AssetSortBy;
import trade.kuharchuk.cryptocompare.domain.AssetType;
import trade.kuharchuk.cryptocompare.domain.Sort;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TopListQuery extends PageRequest {
  private AssetSortBy sortBy;
  private Sort sortDirection;
  private AssetType assetType;

  @Builder
  public TopListQuery(
      final AssetSortBy sortBy,
      final Sort sortDirection,
      final AssetType assetType,
      final Integer page,
      final Integer pageSize) {
    super(page, pageSize);
    this.sortBy = sortBy;
    this.sortDirection = sortDirection;
    this.assetType = assetType;
  }
}
