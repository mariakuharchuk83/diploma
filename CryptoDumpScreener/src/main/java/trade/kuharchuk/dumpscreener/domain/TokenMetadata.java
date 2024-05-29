package trade.kuharchuk.dumpscreener.domain;

public interface TokenMetadata {
  String getSymbol();

  String getName();

  Long deploymentTime();

  NetworkContract getIdentityContract();
}