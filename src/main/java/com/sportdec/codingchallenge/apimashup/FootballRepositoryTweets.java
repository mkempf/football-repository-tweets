package com.sportdec.codingchallenge.apimashup;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collecting Tweets mentioning the the 10 top rated gitHub repositories with topic "football"
 * The tweets are collected using url opeartor of twitter search explained
 * <a href=https://developer.twitter.com/en/docs/tweets/search/guides/standard-operators>here</a>.
 * A mention can therefore be a link to the repository, issue, pull request, issue comment, commit
 */
@Component
public class FootballRepositoryTweets {

    private static final String GIT_HUB_FOOTBALL_TOPIC = "football";
    private static final String GIT_HUB_URL_PREFIX = "github.com/";

    private GitHubSearchService gitHubSearchService;
    private TwitterSearchService twitterSearchService;

    @Inject
    public FootballRepositoryTweets(GitHubSearchService gitHubSearchService, TwitterSearchService twitterSearchService) {
        this.gitHubSearchService = gitHubSearchService;
        this.twitterSearchService = twitterSearchService;
    }



    public List<GitHubRepositoryModel> fetch() {
        GitHubRepositorySearchResult result = gitHubSearchService.searchRepositoryByTopic(GIT_HUB_FOOTBALL_TOPIC);
        return result.getItems().stream().limit(10).map(repository -> {
            GitHubRepositoryModel gitHubRepositoryModel = new GitHubRepositoryModel();
            gitHubRepositoryModel.setUrl(repository.getHtmlURL());
            gitHubRepositoryModel.setDescription(repository.getDescription());
            List<TweetModel> tweets = twitterSearchService.searchTweetsWithUrl(GIT_HUB_URL_PREFIX + repository.getFullName()).getTweets().stream()
                    .map(tweet -> new TweetModel(tweet.getText(), tweet.getCreatedAt(), tweet.getFromUser()))
                    .collect(Collectors.toList());
            gitHubRepositoryModel.setTweets(tweets);
            return gitHubRepositoryModel;
        }).collect(Collectors.toList());
    }
}
