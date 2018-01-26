package com.sportdec.codingchallenge.apimashup;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Service to search for tweets on Twitter using the <a href="https://developer.twitter.com/en/docs/tweets/search/overview/standard">Twitter Standard Search API</a>
 */
@Service
public class TwitterSearchService {

    private static final String URL_SEARCH_PREFIX = "url:";

    private static Logger LOGGER = LoggerFactory.getLogger(TwitterSearchService.class);

    @Value("${app.twitterConfig.consumerKey}")
    private String CONSUMER_KEY;

    @Value("${app.twitterConfig.consumerSecret}")
    private String CONSUMER_SECRET;

    private Twitter api;

    @PostConstruct
    public void init() {
        api = new TwitterTemplate(CONSUMER_KEY, CONSUMER_SECRET);
    }

    public SearchResults searchTweetsWithUrl(String urlSubstring) {
        //TODO: ErrorHandler (someting built in with spring social)
        LOGGER.debug("Search tweet with url containing: {}", urlSubstring);
        return api.searchOperations().search(URL_SEARCH_PREFIX + urlSubstring);
    }
}
