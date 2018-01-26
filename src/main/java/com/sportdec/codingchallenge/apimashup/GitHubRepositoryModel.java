package com.sportdec.codingchallenge.apimashup;

import java.util.List;


public class GitHubRepositoryModel {

    private String url;
    private String description;
    private List<TweetModel> tweets;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TweetModel> getTweets() {
        return tweets;
    }

    public void setTweets(List<TweetModel> tweets) {
        this.tweets = tweets;
    }
}
