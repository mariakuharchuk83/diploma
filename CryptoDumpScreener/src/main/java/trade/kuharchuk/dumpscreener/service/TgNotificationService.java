package trade.kuharchuk.dumpscreener.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import trade.kuharchuk.dumpscreener.exception.NotificationException;
import trade.kuharchuk.dumpscreener.domain.Token;
import trade.kuharchuk.dumpscreener.enums.Network;
import trade.kuharchuk.dumpscreener.event.DumpSignalEvent;

import java.util.Optional;

import static java.lang.String.valueOf;
import static trade.kuharchuk.dumpscreener.util.MathUtil.alternativeMoneyFormat;
import static trade.kuharchuk.dumpscreener.util.MathUtil.calculateAgeInDays;
import static trade.kuharchuk.dumpscreener.util.MathUtil.formatPrice;
import static trade.kuharchuk.dumpscreener.util.MathUtil.getFormattedSpread;

@Service
@Slf4j
public class TgNotificationService {
  private static final String DEX_1inch = "1inch";

  private static final String SIGNAL_CEXES_TEMPLATE = "  ${exchange}: `${price}, ${spread}%`\n";
  private static final String MSG_DIVIDER = "---------------------\n";
  private static final String SIGNAL_MSG_TEMPLATE = """
      *${symbol}/${priceChangePercent}%*
              
      Address: [${tokenAddress}](${tokenURL})
      Network: `${tokenNetwork}`
      Name: *${tokenName}*
      TimeWindow: ${detectionTimeWindow} sec
      MarketCap: ${tokenMarketCap}$
      Volume24H: ${volume24H}$
      Age: ${tokenAge}
      Dex:
        Market: ${dexMarket}
        Price: ${dexPrice}
      """;

  private final TelegramClient telegram;

  public TgNotificationService(Optional<TelegramClient> telegram) {
    this.telegram = telegram.orElse(null);
    telegram.ifPresent(t -> log.info("NotificationService is enabled: telegram"));
  }

  public void sendNotifications(DumpSignalEvent event) {
    final String text = toTgDisplayText(event);
    sendNotification(text);
    System.out.println(text);
  }

  public void sendNotification(String text) {
    try {
      telegram.sendNotification(text, true);
      System.out.println(text);
    } catch (Exception ex) {
      throw new NotificationException(ex);
    }
  }

  public static String toTgDisplayText(DumpSignalEvent event) {
    final Token token = event.getToken();
    final Network network = event.getNetwork();
    final StringBuilder bldr = new StringBuilder(MSG_DIVIDER);
    bldr.append(SIGNAL_MSG_TEMPLATE.replace("${symbol}", token.getSymbol().toUpperCase())
        .replace("${priceChangePercent}", getFormattedSpread(event.getChangePercentage()))
        .replace("${tokenAddress}", token.getContractAddress(network))
        .replace("${tokenURL}", buildTokenUrl(token, network))
        .replace("${tokenNetwork}", network.toString())
        .replace("${tokenName}", token.getName())
        .replace("${detectionTimeWindow}", valueOf(event.getMonitoredTimeWindow().getSeconds()))
        .replace("${tokenMarketCap}", alternativeMoneyFormat(token.getMarketCap()))
        .replace("${volume24H}", alternativeMoneyFormat(token.getUsdVolume24H()))
        .replace("${tokenAge}", token.getDeploymentTime() != null ? calculateAgeInDays(token.getDeploymentTime()) + " days" : "--")
        .replace("${dexMarket}", DEX_1inch)
        .replace("${dexPrice}", formatPrice(event.getCurrentPrice())));
    if (!event.getCexOptions().isEmpty()) {
      bldr.append("__Spread on CEX__:\n");
    }
    event.getCexOptions().values().forEach(spread -> {
      bldr.append(SIGNAL_CEXES_TEMPLATE
          .replace("${exchange}", spread.getSellExchange().toString())
          .replace("${price}", formatPrice(spread.getCurrentPrice()))
          .replace("${spread}", getFormattedSpread(spread.getSpreadPercentage())));
    });
    bldr.append(MSG_DIVIDER);
    return bldr.toString();
  }

  private static String buildTokenUrl(Token token, Network network){
    return "https://dexscreener.com/" + network.toString().toLowerCase() + "/" + token.getContractAddress(network);
  }
}
