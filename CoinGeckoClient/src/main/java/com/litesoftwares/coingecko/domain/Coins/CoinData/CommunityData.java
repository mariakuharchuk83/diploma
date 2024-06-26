package com.litesoftwares.coingecko.domain.Coins.CoinData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommunityData {
    @JsonProperty("facebook_likes")
    private long facebookLikes;
    @JsonProperty("twitter_followers")
    private long twitterFollowers;
    @JsonProperty("reddit_average_posts_48h")
    private BigDecimal redditAveragePosts48h;
    @JsonProperty("reddit_average_comments_48h")
    private BigDecimal redditAverageComments48h;
    @JsonProperty("reddit_subscribers")
    private long redditSubscribers;
    @JsonProperty("reddit_accounts_active_48h")
    private BigDecimal redditAccountsActive48h;
    @JsonProperty("telegram_channel_user_count")
    private long telegramChannelUserCount;

}
