package com.litesoftwares.coingecko.examples;

import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinPriceData;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

public class SimpleExample {
    public static void main(String[] args) {

        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        Map<String, CoinPriceData> bitcoin = client.getPrice(Arrays.asList("bitcoin"), Currency.USD);

        System.out.println(bitcoin);

        BigDecimal bitcoinPrice = bitcoin.get("bitcoin").getUsdPrice();

        System.out.println(bitcoinPrice);
    }
}
