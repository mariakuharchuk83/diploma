package trade.kuharchuk.cryptocompare.ws.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WelcomeMessage extends BaseResponse {
  private String message;
  private long serverUptimeSeconds;
  private String serverName;
  private long serverTimeMs;
  private int clientId;
  private String dataFormat;
  private String socketId;
  private int socketsActive;
  private int socketsRemaining;
  private int rateLimitMaxSecond;
  private int rateLimitMaxMinute;
  private int rateLimitMaxHour;
  private int rateLimitMaxDay;
  private int rateLimitMaxMonth;
  private int rateLimitRemainingSecond;
  private int rateLimitRemainingMinute;
  private int rateLimitRemainingHour;
  private int rateLimitRemainingDay;
  private int rateLimitRemainingMonth;
}
