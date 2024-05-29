package trade.kuharchuk.cryptocompare.service;

import trade.kuharchuk.cryptocompare.response.AssetData;
import trade.kuharchuk.cryptocompare.response.PageableResponse;
import trade.kuharchuk.cryptocompare.service.endpoint.AssetDataApi;
import trade.kuharchuk.cryptocompare.CryptoCompareParams;
import trade.kuharchuk.cryptocompare.client.CryptoCompareClient;
import trade.kuharchuk.cryptocompare.domain.AssetFieldGroup;
import trade.kuharchuk.cryptocompare.domain.AssetSortBy;
import trade.kuharchuk.cryptocompare.domain.LookupPriority;
import trade.kuharchuk.cryptocompare.request.PageRequest;
import trade.kuharchuk.cryptocompare.request.TopListQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static trade.kuharchuk.cryptocompare.domain.AssetFieldGroup.*;

public class AssetDataService {
  private static final Set<AssetFieldGroup> MARKET_STATS_FIELDS =
      Set.of(ID, BASIC, SUPPLY, PRICE, MKT_CAP, VOLUME, CHANGE, TOPLIST_RANK, SUPPORTED_PLATFORMS);
  private static final Set<AssetFieldGroup> ASSET_LINKS_FIELDS = Set.of(ID, SOCIAL, RESOURCE_LINKS);
  private static final int ASSET_CATEGORY_MAX_PAGE_SIZE = 100;
  private static final int ASSET_CATEGORY_MAX_PAGE = 1000;
  public static final int TOP_LIST_MAX_PAGE_SIZE = 100;

  private final AssetDataApi api;
  private final CryptoCompareClient client;

  public AssetDataService(final CryptoCompareParams config) {
    this.client = new CryptoCompareClient(config);
    this.api = client.createService(AssetDataApi.class);
  }

  public AssetData loadAssetMetadata(final String symbol) {
    return client.executeSync(
        api.getAssetMetadata(symbol, MARKET_STATS_FIELDS, LookupPriority.SYMBOL));
  }

  public AssetData loadAssetSocialNetworks(final String symbol) {
    return client.executeSync(
        api.getAssetMetadata(symbol, ASSET_LINKS_FIELDS, LookupPriority.SYMBOL));
  }

  public List<AssetData> getAllAssets() {
    return iterativelyLoadTopList(AssetSortBy.CIRCULATING_MKT_CAP_USD, PageRequest.unpaged());
  }

  public PageableResponse<AssetData> getAssetsTopList(final TopListQuery query) {
    Objects.requireNonNull(query);
    if (query.getPage() != null && query.getPage() > ASSET_CATEGORY_MAX_PAGE) {
      throw new IllegalArgumentException("page must be less then " + ASSET_CATEGORY_MAX_PAGE);
    }
    if (query.getPageSize() != null && query.getPageSize() > ASSET_CATEGORY_MAX_PAGE_SIZE) {
      throw new IllegalArgumentException(
          "page size must be less then " + ASSET_CATEGORY_MAX_PAGE_SIZE);
    }
    return client.executeSync(
        api.getTopList(
            query.getSortBy(),
            query.getSortDirection(),
            query.getAssetType(),
            MARKET_STATS_FIELDS,
            query.getPage(),
            query.getPageSize()));
  }

  public List<AssetData> iterativelyLoadTopList(
      final AssetSortBy category, final PageRequest pageRequest) {
    final List<AssetData> result = new ArrayList<>();
    final TopListQuery query = buildTopListQuery(category, 1, TOP_LIST_MAX_PAGE_SIZE);
    PageableResponse<AssetData> pageResponse;
    final long itemsToLoad =
        pageRequest.isPaged()
            ? pageRequest.getOffset() + pageRequest.getPageSize()
            : Integer.MAX_VALUE;
    do {
      pageResponse = getAssetsTopList(query);
      if (pageResponse.getList().isEmpty()) {
        break;
      }

      result.addAll(pageResponse.getList());

      query.setPage(query.getPage() + 1);
    } while (result.size() < itemsToLoad
        && query.getPage() * query.getPageSize() < pageResponse.getStats().getTotalAssets());
    Stream<AssetData> stream = result.stream();
    if (pageRequest.isPaged()) {
      stream = stream.skip(pageRequest.getOffset()).limit(pageRequest.getPageSize());
    }
    return stream.collect(Collectors.toList());
  }

  public static TopListQuery buildTopListQuery(final AssetSortBy category, final int page, final int size) {
    final TopListQuery.TopListQueryBuilder bldr = TopListQuery.builder().sortBy(category).page(page).pageSize(size);
    return bldr.build();
  }

}
