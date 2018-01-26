package com.sportdec.codingchallenge.apimashup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterSearchService {

    private static Logger LOGGER = LoggerFactory.getLogger(TwitterSearchService.class);

    @Value("${app.twitterConfig.consumerKey}")
    private String CONSUMER_KEY;

    @Value("${app.twitterConfig.consumerSecret}")
    private String CONSUMER_SECRET;


    public void search(String query) {
        Twitter twitter = new TwitterTemplate(CONSUMER_KEY, CONSUMER_SECRET);
        SearchResults results = twitter.searchOperations().search(query);
        LOGGER.info(results.getTweets().get(0).getText());
    }
}
