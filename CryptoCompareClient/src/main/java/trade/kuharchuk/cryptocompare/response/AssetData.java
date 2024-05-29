package trade.kuharchuk.cryptocompare.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetData implements Serializable {

  @JsonAlias({"ID"})
  private Integer id;

  @JsonAlias({"TYPE"})
  private String type;

  @JsonAlias({"ID_PARENT_ASSET"})
  private Integer idParentAsset;

  @JsonAlias({"SYMBOL"})
  private String symbol;

  @JsonAlias({"URI"})
  private String uri;

  @JsonAlias({"ASSET_TYPE"})
  private String assetType;

  @JsonAlias({"PARENT_ASSET_SYMBOL"})
  private String parentAssetSymbol;

  @JsonAlias({"CREATED_ON"})
  private Long createdOn;

  @JsonAlias({"UPDATED_ON"})
  private Long updatedOn;

  @JsonAlias({"PUBLIC_NOTICE"})
  private String publicNotice;

  @JsonAlias({"NAME"})
  private String name;

  @JsonAlias({"LOGO_URL"})
  private String logoUrl;

  @JsonAlias({"LAUNCH_DATE"})
  private Long launchDate;

  @JsonAlias({"ASSET_ALTERNATIVE_IDS"})
  private List<AssetAlternativeId> assetAlternativeIds;

  @JsonAlias({"ASSET_DESCRIPTION_SNIPPET"})
  private String assetDescriptionSnippet;

  @JsonAlias({"ASSET_DECIMAL_POINTS"})
  private Integer assetDecimalPoints;

  @JsonAlias({"SUPPORTED_PLATFORMS"})
  private List<SupportedPlatform> supportedPlatforms;

  @JsonAlias({"ASSET_CUSTODIANS"})
  private List<Map<String, Object>> assetCustodians;

  @JsonAlias({"ASSET_SECURITY_METRICS"})
  private List<AssetSecurityMetric> assetSecurityMetrics;

  @JsonAlias({"SUPPLY_MAX"})
  private BigDecimal supplyMax;

  @JsonAlias({"SUPPLY_ISSUED"})
  private BigDecimal supplyIssued;

  @JsonAlias({"SUPPLY_TOTAL"})
  private BigDecimal supplyTotal;

  @JsonAlias({"SUPPLY_CIRCULATING"})
  private BigDecimal supplyCirculating;

  @JsonAlias({"SUPPLY_FUTURE"})
  private BigDecimal supplyFuture;

  @JsonAlias({"SUPPLY_LOCKED"})
  private BigDecimal supplyLocked;

  @JsonAlias({"SUPPLY_BURNT"})
  private BigDecimal supplyBurnt;

  @JsonAlias({"SUPPLY_STAKED"})
  private BigDecimal supplyStaked;

  @JsonAlias({"LAST_BLOCK_MINT"})
  private BigDecimal lastBlockMint;

  @JsonAlias({"LAST_BLOCK_BURN"})
  private BigDecimal lastBlockBurn;

  @JsonAlias({"BURN_ADDRESSES"})
  private List<String> burnAddresses;

  @JsonAlias({"LOCKED_ADDRESSES"})
  private List<String> lockedAddresses;

  @JsonAlias({"HAS_SMART_CONTRACT_CAPABILITIES"})
  private Integer hasSmartContractCapabilities;

  @JsonAlias({"TARGET_BLOCK_MINT"})
  private BigDecimal targetBlockMint;

  @JsonAlias({"TARGET_BLOCK_TIME"})
  private BigDecimal targetBlockTime;

  @JsonAlias({"LAST_BLOCK_NUMBER"})
  private Integer lastBlockNumber;

  @JsonAlias({"LAST_BLOCK_TIMESTAMP"})
  private Long lastBlockTimestamp;

  @JsonAlias({"LAST_BLOCK_TIME"})
  private BigDecimal lastBlockTime;

  @JsonAlias({"LAST_BLOCK_SIZE"})
  private BigDecimal lastBlockSize;

  @JsonAlias({"LAST_BLOCK_ISSUER"})
  private String lastBlockIssuer;

  @JsonAlias({"LAST_BLOCK_TRANSACTION_FEE_TOTAL"})
  private BigDecimal lastBlockTransactionFeeTotal;

  @JsonAlias({"LAST_BLOCK_TRANSACTION_COUNT"})
  private BigDecimal lastBlockTransactionCount;

  @JsonAlias({"LAST_BLOCK_HASHES_PER_SECOND"})
  private BigDecimal lastBlockHashesPerSecond;

  @JsonAlias({"LAST_BLOCK_DIFFICULTY"})
  private BigDecimal lastBlockDifficulty;

  @JsonAlias({"SUPPORTED_STANDARDS"})
  private List<Map<String, Object>> supportedStandards;

  @JsonAlias({"LAYER_TWO_SOLUTIONS"})
  private List<Map<String, Object>> layerTwoSolutions;

  @JsonAlias({"PRIVACY_SOLUTIONS"})
  private List<Map<String, Object>> privacySolutions;

  @JsonAlias({"CODE_REPOSITORIES"})
  private List<CodeRepository> codeRepositories;

  @JsonAlias({"SUBREDDITS"})
  private List<Reddit> subreddits;

  @JsonAlias({"TWITTER_ACCOUNTS"})
  private List<TwitterAccount> twitterAccounts;

  @JsonAlias({"DISCORD_SERVERS"})
  private List<DiscordServer> discordServers;

  @JsonAlias({"TELEGRAM_GROUPS"})
  private List<TelegramGroup> telegramGroups;

  @JsonAlias({"OTHER_SOCIAL_NETWORKS"})
  private List<OtherSocialNetwork> otherSocialNetworks;

  @JsonAlias({"HELD_TOKEN_SALE"})
  private BigDecimal heldTokenSale;

  @JsonAlias({"HELD_EQUITY_SALE"})
  private BigDecimal heldEquitySale;

  @JsonAlias({"WEBSITE_URL"})
  private String websiteUrl;

  @JsonAlias({"BLOG_URL"})
  private String blogUrl;

  @JsonAlias({"WHITE_PAPER_URL"})
  private String whitePaperUrl;

  @JsonAlias({"OTHER_DOCUMENT_URLS"})
  private List<Object> otherDocumentUrls;

  @JsonAlias({"EXPLORER_ADDRESSES"})
  private List<Map<String, Object>> explorerAddresses;

  @JsonAlias({"ASSET_INDUSTRIES"})
  private List<Map<String, Object>> assetIndustries;

  @JsonAlias({"CONSENSUS_MECHANISMS"})
  private List<Map<String, Object>> consensusMechanisms;

  @JsonAlias({"CONSENSUS_ALGORITHM_TYPES"})
  private List<Map<String, Object>> consensusAlgorithmTypes;

  @JsonAlias({"HASHING_ALGORITHM_TYPES"})
  private List<Map<String, Object>> hashingAlgorithmTypes;

  @JsonAlias({"PRICE_USD"})
  private BigDecimal priceUsd;

  @JsonAlias({"PRICE_USD_SOURCE"})
  private String priceUsdSource;

  @JsonAlias({"PRICE_USD_LAST_UPDATE_TS"})
  private Long priceUsdLastUpdateTs;

  @JsonAlias({"MKT_CAP_PENALTY"})
  private BigDecimal mktCapPenalty;

  @JsonAlias({"CIRCULATING_MKT_CAP_USD"})
  private BigDecimal circulatingMktCapUsd;

  @JsonAlias({"TOTAL_MKT_CAP_USD"})
  private BigDecimal totalMktCapUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_QUOTE_VOLUME_TOP_TIER_DIRECT_USD"})
  private BigDecimal spotMoving24HourQuoteVolumeTopTierDirectUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_QUOTE_VOLUME_DIRECT_USD"})
  private BigDecimal spotMoving24HourQuoteVolumeDirectUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_QUOTE_VOLUME_TOP_TIER_USD"})
  private BigDecimal spotMoving24HourQuoteVolumeTopTierUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_QUOTE_VOLUME_USD"})
  private BigDecimal spotMoving24HourQuoteVolumeUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_CHANGE_USD"})
  private BigDecimal spotMoving24HourChangeUsd;

  @JsonAlias({"SPOT_MOVING_24_HOUR_CHANGE_PERCENTAGE_USD"})
  private BigDecimal spotMoving24HourChangePercentageUsd;

  @JsonAlias({"TOPLIST_BASE_RANK"})
  private ToplistBaseRank toplistBaseRank;

  @JsonAlias({"ASSET_DESCRIPTION"})
  private String assetDescription;

  @JsonAlias({"PROJECT_LEADERS"})
  private List<Map<String, Object>> projectLeaders;

  @JsonAlias({"ASSOCIATED_CONTACT_DETAILS"})
  private List<Map<String, Object>> associatedContactDetails;

  @JsonAlias({"SEO_TITLE"})
  private String seoTitle;

  @JsonAlias({"SEO_DESCRIPTION"})
  private String seoDescription;

  // Nested classes
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AssetAlternativeId implements Serializable {
    @JsonAlias({"NAME"})
    private String name;

    @JsonAlias({"ID"})
    private String id;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class SupportedPlatform implements Serializable {
    @JsonAlias({"BLOCKCHAIN"})
    private String blockchain;

    @JsonAlias({"TOKEN_STANDARD"})
    private String tokenStandard;

    @JsonAlias({"BRIDGE_OPERATOR"})
    private String bridgeOperator;

    @JsonAlias({"IS_ASSET_ISSUER"})
    private Boolean isAssetIssuer;

    @JsonAlias({"EXPLORER_URL"})
    private String explorerUrl;

    @JsonAlias({"SMART_CONTRACT_ADDRESS"})
    private String smartContractAddress;

    @JsonAlias({"LAUNCH_DATE"})
    private Long launchDate;

    @JsonAlias({"TRADING_AS"})
    private String tradingAs;

    @JsonAlias({"DECIMALS"})
    private Integer decimals;

    @JsonAlias({"IS_INHERITED"})
    private Boolean isInherited;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AssetSecurityMetric implements Serializable {
    @JsonAlias({"NAME"})
    private String name;

    @JsonAlias({"OVERALL_SCORE"})
    private Double overallScore;

    @JsonAlias({"OVERALL_RANK"})
    private Integer overallRank;

    @JsonAlias({"UPDATED_AT"})
    private Long updatedAt;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ToplistBaseRank implements Serializable {
    @JsonAlias("LAUNCH_DATE")
    private Integer launchDate;

    @JsonAlias("CIRCULATING_MKT_CAP_USD")
    private Integer circulatingMktCapUsd;

    @JsonAlias("TOTAL_MKT_CAP_USD")
    private Integer totalMktCapUsd;

    @JsonAlias("SPOT_MOVING_24_HOUR_QUOTE_VOLUME_USD")
    private Integer spotMoving24HourQuoteVolumeUsd;

    @JsonAlias("SPOT_MOVING_24_HOUR_CHANGE_PERCENTAGE_USD")
    private Integer spotMoving24HourChangePercentageUsd;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CodeRepository {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("MAKE_3RD_PARTY_REQUEST")
    private boolean make3rdPartyRequest;

    @JsonAlias("OPEN_ISSUES")
    private int openIssues;

    @JsonAlias("CLOSED_ISSUES")
    private int closedIssues;

    @JsonAlias("OPEN_PULL_REQUESTS")
    private int openPullRequests;

    @JsonAlias("CLOSED_PULL_REQUESTS")
    private int closedPullRequests;

    @JsonAlias("CONTRIBUTORS")
    private int contributors;

    @JsonAlias("FORKS")
    private int forks;

    @JsonAlias("STARS")
    private int stars;

    @JsonAlias("SUBSCRIBERS")
    private int subscribers;

    @JsonAlias("LAST_UPDATED_TS")
    private long lastUpdatedTimestamp;

    @JsonAlias("CREATED_AT")
    private long createdAt;

    @JsonAlias("UPDATED_AT")
    private long updatedAt;

    @JsonAlias("LAST_PUSH_TS")
    private long lastPushTimestamp;

    @JsonAlias("CODE_SIZE_IN_BYTES")
    private long codeSizeInBytes;

    @JsonAlias("IS_FORK")
    private boolean isFork;

    @JsonAlias("LANGUAGE")
    private String language;

    @JsonAlias("FORKED_ASSET_DATA")
    private Object forkedAssetData;

    @JsonAlias("ENDPOINTS_USED")
    private List<Object> endpointsUsed;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Reddit {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("MAKE_3RD_PARTY_REQUEST")
    private boolean make3rdPartyRequest;

    @JsonAlias("NAME")
    private String name;

    @JsonAlias("CURRENT_ACTIVE_USERS")
    private int currentActiveUsers;

    @JsonAlias("AVERAGE_POSTS_PER_DAY")
    private double averagePostsPerDay;

    @JsonAlias("AVERAGE_POSTS_PER_HOUR")
    private double averagePostsPerHour;

    @JsonAlias("AVERAGE_COMMENTS_PER_DAY")
    private double averageCommentsPerDay;

    @JsonAlias("AVERAGE_COMMENTS_PER_HOUR")
    private double averageCommentsPerHour;

    @JsonAlias("SBUSCRIBERS")
    private int subscribers;

    @JsonAlias("COMMUNITY_CREATED_AT")
    private long communityCreatedAt;

    @JsonAlias("LAST_UPDATED_TS")
    private long lastUpdatedTimestamp;

    @JsonAlias("ENDPOINTS_USED")
    private List<Object> endpointsUsed;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TwitterAccount {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("MAKE_3RD_PARTY_REQUEST")
    private boolean make3rdPartyRequest;

    @JsonAlias("NAME")
    private String name;

    @JsonAlias("USERNAME")
    private String username;

    @JsonAlias("VERIFIED")
    private boolean verified;

    @JsonAlias("VERIFIED_TYPE")
    private String verifiedType;

    @JsonAlias("FOLLOWING")
    private int following;

    @JsonAlias("FOLLOWERS")
    private int followers;

    @JsonAlias("FAVOURITES")
    private int favourites;

    @JsonAlias("LISTS")
    private int lists;

    @JsonAlias("STATUSES")
    private int statuses;

    @JsonAlias("ACCOUNT_CREATED_AT")
    private long accountCreatedAt;

    @JsonAlias("LAST_UPDATED_TS")
    private long lastUpdatedTimestamp;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class DiscordServer {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("MAKE_3RD_PARTY_REQUEST")
    private boolean make3rdPartyRequest;

    @JsonAlias("NAME")
    private String name;

    @JsonAlias("TOTAL_MEMBERS")
    private int totalMembers;

    @JsonAlias("CURRENT_ACTIVE_USERS")
    private int currentActiveUsers;

    @JsonAlias("PREMIUM_SUBSCRIBERS")
    private int premiumSubscribers;

    @JsonAlias("LAST_UPDATED_TS")
    private long lastUpdatedTimestamp;

    @JsonAlias("ENDPOINTS_USED")
    private List<Object> endpointsUsed;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class TelegramGroup {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("MAKE_3RD_PARTY_REQUEST")
    private boolean make3rdPartyRequest;

    @JsonAlias("NAME")
    private String name;

    @JsonAlias("USERNAME")
    private String username;

    @JsonAlias("MEMBERS")
    private int members;

    @JsonAlias("LAST_UPDATED_TS")
    private long lastUpdatedTimestamp;

    @JsonAlias("ENDPOINTS_USED")
    private List<Object> endpointsUsed;
  }

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class OtherSocialNetwork {
    @JsonAlias("URL")
    private String url;

    @JsonAlias("NAME")
    private String name;
  }
}
