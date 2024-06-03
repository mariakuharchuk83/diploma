package trade.kuharchuk.dumpscreener.service;

import com.google.common.collect.Lists;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.domain.Coins.CoinList;
import com.litesoftwares.coingecko.domain.Coins.CoinPriceData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import trade.kuharchuk.dumpscreener.config.AppProperties;
import trade.kuharchuk.dumpscreener.config.GlobalConstants;
import trade.kuharchuk.dumpscreener.domain.NetworkContract;
import trade.kuharchuk.dumpscreener.domain.Token;
import trade.kuharchuk.dumpscreener.domain.TradePair;
import trade.kuharchuk.dumpscreener.event.MetadataRefreshedEvent;
import trade.kuharchuk.dumpscreener.service.dexscreener.DexscreenerClient;
import trade.kuharchuk.dumpscreener.service.dexscreener.TokensResponse;
import trade.kuharchuk.dumpscreener.enums.Network;
import trade.wayruha.cryptocompare.domain.AssetSortBy;
import trade.wayruha.cryptocompare.request.PageRequest;
import trade.wayruha.cryptocompare.response.AssetData;
import trade.wayruha.cryptocompare.response.ExchangeData;
import trade.wayruha.cryptocompare.response.InstrumentData;
import trade.wayruha.cryptocompare.response.InstrumentMapping;
import trade.wayruha.cryptocompare.service.AssetDataService;
import trade.wayruha.cryptocompare.service.SpotDataService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MetadataService {
  private static final int COINGECKO_REQUEST_MAX_LENGTH = 5000;
  private static final int COINGECKO_REQUEST_BACKOFF = 2000;
  private final AppProperties properties;
  private final CoinGeckoApiClient coinGeckoApiClient;
  private final AssetDataService assetDataService;
  private final SpotDataService spotDataService;
  private final DexscreenerClient dexscreenerClient;
  private final ExecutorService executorService;
  private final ApplicationEventPublisher eventPublisher;
  private final List<Token> coinsData = new ArrayList<>();
  @Getter
  private LocalDateTime lastUpdate;
  //duplication of data for easy access
  private final Map<NetworkContract, Token> duplicateCoinsMap = new HashMap<>();
  private final Map<Network, List<NetworkContract>> contractsByNetworkMap = new HashMap<>();

  public void updateMetadata() throws ExecutionException, InterruptedException {
    final LocalDateTime start = LocalDateTime.now();
    log.debug("Updating metadata...");
    final Future<List<CoinList>> cgCoinsListFuture = executorService.submit(coinGeckoApiClient::getCoinList);
    final Future<List<AssetData>> ccMetadataFuture = executorService.submit(() ->
        assetDataService.iterativelyLoadTopList(AssetSortBy.CIRCULATING_MKT_CAP_USD, PageRequest.unpaged()));
    final List<CoinList> coins = cgCoinsListFuture.get();

    final List<Token> chainSupportedTokens = buildTokenData(coins);
    final List<Token> filteredTokens = filterTokens(chainSupportedTokens);

    log.debug("Tokens Filtered: {}. Existing on chains: {}. Supported by CoinGecko: {}.", filteredTokens.size(), coins.size(), chainSupportedTokens.size());
    populateCryptoCompareIds(filteredTokens, ccMetadataFuture.get());
    populateTradePair(filteredTokens);

    updateMetadata(filteredTokens);
    eventPublisher.publishEvent(new MetadataRefreshedEvent(filteredTokens, Duration.between(start, lastUpdate)));
  }

  public Token getTokenByContract(NetworkContract contract) {
    if (contract == null) return null;
    return duplicateCoinsMap.get(contract);
  }

  public List<NetworkContract> getContractsByNetwork(Network network) {
    return contractsByNetworkMap.get(network);
  }

  private void updateMetadata(List<Token> filteredTokens) {
    this.lastUpdate = LocalDateTime.now();
    this.coinsData.clear();
    this.coinsData.addAll(filteredTokens);
    this.duplicateCoinsMap.clear();
    filteredTokens.forEach(token ->
        token.getContracts().forEach(contract -> this.duplicateCoinsMap.put(contract, token))
    );
    this.contractsByNetworkMap.clear();
    final Map<Network, List<NetworkContract>> contractsByNetwork = filteredTokens.stream().flatMap(token -> token.getContracts().stream()).collect(Collectors.groupingBy(NetworkContract::getNetwork));
    this.contractsByNetworkMap.putAll(contractsByNetwork);
  }

  @NotNull
  private List<Token> buildTokenData(List<CoinList> coins) {
    final List<Token> tokens = coins.stream()
        .map(coin -> {
          final Map<String, String> platforms = coin.getPlatforms();
          final List<NetworkContract> contracts = platforms.keySet().stream()
              .map(Network::getByCgName)
              .filter(Objects::nonNull)
              .filter(net -> properties.getNetworks().contains(net))
              .map(net -> NetworkContract.of(platforms.get(net.getCgName()), net))
              .toList();
          return contracts.isEmpty() ? null :
              Token.builder()
                  .coingeckoId(coin.getId())
                  .cgSymbol(coin.getSymbol())
                  .name(coin.getName())
                  .tradePairs(new HashMap<>())
                  .contracts(contracts)
                  .build();
        })
        .filter(Objects::nonNull)
        .toList();
    return tokens;
  }

  /**
   * @return Map of CoinGeckoId to CoinPriceData
   */
  private Map<Token, CoinPriceData> getCoingeckoMetadata(List<Token> tokens) {
    long start = System.currentTimeMillis();
    final Map<String, CoinPriceData> runningMetadataMap = new HashMap<>();
    StringBuilder sublistBuilder = new StringBuilder();
    List<String> idSublist = new ArrayList<>();

    for (Token coin : tokens) {
      final String coinId = coin.getCoingeckoId();
      final int coinIdLength = coinId.length();

      if (sublistBuilder.length() + coinIdLength + 1 > COINGECKO_REQUEST_MAX_LENGTH) {
        runningMetadataMap.putAll(fetchCoinGeckoMetadata(idSublist));
        sublistBuilder = new StringBuilder();
        idSublist = new ArrayList<>();
      }

      if (!sublistBuilder.isEmpty()) {
        sublistBuilder.append(",");
      }
      sublistBuilder.append(coinId);
      idSublist.add(coinId);
    }

    if (!sublistBuilder.isEmpty()) {
      runningMetadataMap.putAll(fetchCoinGeckoMetadata(idSublist));
    }
    final Map<Token, CoinPriceData> resultMetadata = tokens.stream()
        .filter(token -> runningMetadataMap.containsKey(token.getCoingeckoId()))
        .collect(Collectors.toMap(token -> token, token -> runningMetadataMap.get(token.getCoingeckoId())));
    log.debug("Fetched CoinGecko metadata: {} items, {}ms.", resultMetadata.size(), System.currentTimeMillis() - start);
    return resultMetadata;
  }

  private Map<String, CoinPriceData> fetchCoinGeckoMetadata(List<String> sublist) {
    log.debug("fetching partial cg metadata: {}", sublist.size());
    try {
      Thread.sleep(COINGECKO_REQUEST_BACKOFF);
      return coinGeckoApiClient.getCoinPriceData(sublist);
    } catch (Exception ex) {
      log.error("Error fetching CoinGecko metadata, batchSize={}", sublist.size(), ex);
      return Map.of();
    }
  }

  private List<Token> filterTokens(List<Token> tokens) {
    long start = System.currentTimeMillis();
    final Map<Token, CoinPriceData> cgMetadata = getCoingeckoMetadata(tokens); //todo sublist for quicker development
    final List<Token> filteredTokens = cgMetadata.entrySet().stream()
        .filter(Objects::nonNull)
        .filter(e -> properties.getVolume24h() == null ||
            (e.getValue().getUsdVolume24H() != null && e.getValue().getUsdVolume24H().compareTo(properties.getVolume24h()) > 0))
        .filter(e -> properties.getMarketCap() == null
            || (e.getValue().getMarketCap() != null && e.getValue().getMarketCap().compareTo(properties.getMarketCap()) > 0))
        .map(Map.Entry::getKey)
        .toList();

    final List<Pair<Token, NetworkContract>> networkContracts = filteredTokens.stream()
        .flatMap(token -> token.getContracts().stream().map(contract -> Pair.of(token, contract))).toList();
    final List<Pair<Token, NetworkContract>> filteredNetworkContracts = new ArrayList<>();
    Lists.partition(networkContracts, GlobalConstants.DEXSCREENER_TOKEN_COUNT_THRESHOLD).forEach(sublist -> {
      final List<String> contracts = sublist.stream().map(Pair::getRight).map(NetworkContract::getContractAddress).toList();
      final TokensResponse response = dexscreenerClient.getMetadata(contracts);
      final List<String> filtered = response.getPairs().stream().filter(pair -> {
                final BigDecimal liquidity = Optional.ofNullable(pair.getLiquidity().get("usd")).orElse(BigDecimal.ZERO);
                final BigDecimal volume = Optional.ofNullable(pair.getVolume().get("h24")).orElse(BigDecimal.ZERO);
                return liquidity.compareTo(properties.getLiquidity()) >= 0
                    && volume.compareTo(properties.getVolume24h()) >= 0;
              }
          ).map(pair -> pair.getBaseToken().getAddress())
          .distinct()
          .filter(Objects::nonNull)
          .toList();
      final List<Pair<Token, NetworkContract>> pairs = sublist.stream().filter(pair -> filtered.stream()
          .filter(Objects::nonNull)
          .anyMatch(address -> address
              .equalsIgnoreCase(pair.getRight().getContractAddress())))
          .toList();
      log.debug("filtered part of tokens, filtered: {}/{}", pairs.size(), sublist.size());
      filteredNetworkContracts.addAll(pairs);
    });

    log.debug("filtered tokens: {} items, {}ms", filteredNetworkContracts.size(), System.currentTimeMillis() - start);
    return filteredNetworkContracts.stream().map(Pair::getLeft).distinct().toList();
  }

  private void populateTradePair(List<Token> tokens) {
    long start = System.currentTimeMillis();
    properties.getCexes().forEach(cex -> {
      final Map<String, ExchangeData> availableMarkets = spotDataService.getAvailableMarkets(cex.getCcName(), List.of());
      if (!availableMarkets.containsKey(cex.getCcName())) return;
      final ExchangeData data = availableMarkets.get(cex.getCcName());
      final List<InstrumentMapping> instruments = data.getInstruments().values().stream().map(InstrumentData::getInstrumentMapping).toList();
      instruments.stream()
          .filter(instrument -> properties.getStableCoins().stream().anyMatch(stableCoin -> stableCoin.equalsIgnoreCase(instrument.getQuote())))
          .collect(Collectors.groupingBy(InstrumentMapping::getBase))
          .values()
          .stream()
          .map(dupes -> dupes.stream().findFirst())
          .filter(Optional::isPresent)
          .map(Optional::get)
          .forEach(instrument -> {
            tokens.stream()
                .filter(token -> instrument.getBase().equalsIgnoreCase(token.getCcSymbol()))
                .forEach(token -> {
                  token.getTradePairs().put(cex, new TradePair(instrument.getBase(), instrument.getQuote()));
                });
          });
    });
    log.debug("Fetched USD trade pair for every CEX: {} items, {}ms", tokens.size(), System.currentTimeMillis() - start);
    final long count = tokens.stream().filter(t -> t.getCcSymbol() == null).count();
    log.debug("Tokens without cryptoCompareSymbol: {}", count);
  }

  private static void populateCryptoCompareIds(List<Token> tokens, List<AssetData> metadata) {
    long start = System.currentTimeMillis();
    final Map<NetworkContract, AssetData> assetDataMap = metadata.stream()
        .flatMap(assetData -> assetData.getSupportedPlatforms().stream()
            .filter(platform -> Network.getByCcName(platform.getBlockchain()) != null)
            .filter(platform -> platform.getSmartContractAddress() != null)
            .map(platform -> NetworkContract.of(platform.getSmartContractAddress(), Network.getByCcName(platform.getBlockchain())))
            .map(contract -> new AbstractMap.SimpleEntry<>(contract, assetData)))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue));

    tokens.forEach(token -> {
      token.getContracts().stream()
          .map(assetDataMap::get)
          .filter(Objects::nonNull)
          .findFirst()
          .ifPresent(assetData -> {
            token.setDeploymentTime(assetData.getCreatedOn());
            token.setCcId(assetData.getName());
            token.setCcSymbol(assetData.getSymbol());
            token.setMarketCap(assetData.getTotalMktCapUsd());
            token.setUsdVolume24H(assetData.getSpotMoving24HourQuoteVolumeUsd());
          });
    });
    log.debug("Supported by CryptoCompare: {} items, {}ms", tokens.size(), System.currentTimeMillis() - start);
  }

  public List<Token> getTokens() {
    return new ArrayList<>(coinsData);
  }
}
