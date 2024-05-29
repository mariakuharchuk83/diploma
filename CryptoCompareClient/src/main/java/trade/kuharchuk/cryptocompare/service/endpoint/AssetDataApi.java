package trade.kuharchuk.cryptocompare.service.endpoint;

import trade.kuharchuk.cryptocompare.response.AssetData;
import trade.kuharchuk.cryptocompare.response.DataApiResponse;
import trade.kuharchuk.cryptocompare.response.PageableResponse;
import trade.kuharchuk.cryptocompare.domain.AssetFieldGroup;
import trade.kuharchuk.cryptocompare.domain.AssetSortBy;
import trade.kuharchuk.cryptocompare.domain.AssetType;
import trade.kuharchuk.cryptocompare.domain.LookupPriority;
import trade.kuharchuk.cryptocompare.domain.Sort;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.Set;

public interface AssetDataApi {
  @GET("asset/v1/metadata")
  Call<DataApiResponse<AssetData>> getAssetMetadata(
      @Query("asset") String asset,
      @Query("groups") Set<AssetFieldGroup> groups,
      @Query("asset_lookup_priority") LookupPriority lookupPriority);

  @GET("asset/v1/top/list")
  Call<DataApiResponse<PageableResponse<AssetData>>> getTopList(
      @Query("sort_by") AssetSortBy sortBy,
      @Query("sort_direction") Sort sortDirection,
      @Query("asset_type") AssetType assetType,
      @Query("groups") Set<AssetFieldGroup> groups,
      @Query("page") Integer page,
      @Query("page_size") Integer pageSize);
}
