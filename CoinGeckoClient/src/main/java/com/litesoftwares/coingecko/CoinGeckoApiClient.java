package com.litesoftwares.coingecko;

import com.litesoftwares.coingecko.domain.*;
import com.litesoftwares.coingecko.domain.Coins.*;
import com.litesoftwares.coingecko.domain.Events.EventCountries;
import com.litesoftwares.coingecko.domain.Events.EventTypes;
import com.litesoftwares.coingecko.domain.Events.Events;
import com.litesoftwares.coingecko.domain.ExchangeRates.ExchangeRates;
import com.litesoftwares.coingecko.domain.Exchanges.*;
import com.litesoftwares.coingecko.domain.Global.DecentralizedFinanceDefi;
import com.litesoftwares.coingecko.domain.Global.Global;
import com.litesoftwares.coingecko.domain.Search.Search;
import com.litesoftwares.coingecko.domain.Search.Trending;
import com.litesoftwares.coingecko.domain.Status.StatusUpdates;

import java.util.List;
import java.util.Map;

public interface CoinGeckoApiClient {
    Ping ping();
    Map<String, CoinPriceData> getCoinPriceData(List<String> ids);

    Map<String, CoinPriceData> getPrice(List<String> ids, String vsCurrencies);

    Map<String, CoinPriceData> getPrice(List<String> ids, String vsCurrencies, boolean includeMarketCap, boolean include24hrVol,
                                              boolean include24hrChange, boolean includeLastUpdatedAt);

    Map<String, Map<String, Double>> getTokenPrice(String id, String contractAddress, String vsCurrencies);

    Map<String, Map<String, Double>> getTokenPrice(String id, String contractAddress, String vsCurrencies, boolean includeMarketCap,
                         boolean include24hrVol, boolean include24hrChange, boolean includeLastUpdatedAt);

    List<String> getSupportedVsCurrencies();

    List<CoinList> getCoinList();

    List<CoinMarkets> getCoinMarkets(String vsCurrency);

    List<CoinMarkets> getCoinMarkets(String vsCurrency,  String ids, String order,  Integer perPage, Integer page,  boolean sparkline, String priceChangePercentage);

    List<CoinMarkets> getCoinMarkets(String vsCurrency,  String ids, String category, String order,  Integer perPage, Integer page,  boolean sparkline, String priceChangePercentage);

    CoinFullData getCoinById(String id);

    CoinFullData getCoinById(String id, boolean localization, boolean tickers, boolean marketData, boolean communityData, boolean developerData, boolean sparkline);

    CoinTickerById getCoinTickerById(String id);

    CoinTickerById getCoinTickerById(String id, String exchangeIds, Integer page, String order);

    CoinHistoryById getCoinHistoryById(String id, String date);

    CoinHistoryById getCoinHistoryById(String id, String data, boolean localization);

    MarketChart getCoinMarketChartById(String id, String vsCurrency, Integer days);

    MarketChart getCoinMarketChartById(String id, String vsCurrency, Integer days, String interval);

    MarketChart getCoinMarketChartRangeById(String id, String vsCurrency, String from, String to);

    List<List<String>> getCoinOHLC(String id, String vsCurrency, Integer days);

    StatusUpdates getCoinStatusUpdateById(String id);

    StatusUpdates getCoinStatusUpdateById(String id, Integer perPage, Integer page);

    CoinFullData getCoinInfoByContractAddress(String id, String contractAddress);

    List<AssetPlatforms> getAssetPlatforms();

    List<Exchanges> getExchanges();

    List<Exchanges> getExchanges(int perPage, int page);

    List<ExchangesList> getExchangesList();

    ExchangeById getExchangesById(String id);

    ExchangesTickersById getExchangesTickersById(String id);

    ExchangesTickersById getExchangesTickersById(String id, String coinIds, Integer page, String order);

    StatusUpdates getExchangesStatusUpdatesById(String id);

    StatusUpdates getExchangesStatusUpdatesById(String id, Integer perPage, Integer page);

    List<List<String>> getExchangesVolumeChart(String id, Integer days);

    @Deprecated
    StatusUpdates getStatusUpdates();

    @Deprecated
    StatusUpdates getStatusUpdates(String category, String projectType, Integer perPage, Integer page);

    @Deprecated
    Events getEvents();

    @Deprecated
    Events getEvents(String countryCode, String type, Integer page, boolean upcomingEventsOnly, String fromDate, String toDate);

    @Deprecated
    EventCountries getEventsCountries();

    @Deprecated
    EventTypes getEventsTypes();

    ExchangeRates getExchangeRates();

    Trending getTrending();

    Search getSearchResult(String query);

    Global getGlobal();

    DecentralizedFinanceDefi getDecentralizedFinanceDefi();

    void shutdown();
}
