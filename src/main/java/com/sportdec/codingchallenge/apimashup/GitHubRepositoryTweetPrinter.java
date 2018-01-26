package com.sportdec.codingchallenge.apimashup;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * Prints a list of {@link GitHubRepositoryModel} in an appropriate manner
 */
@Component
public class GitHubRepositoryTweetPrinter {

    private static final String GIT_HUB_URL_LINE_TEMPLATE = "{0}: GitHub Repository: {1}";
    private static final String GIT_HUB_SUMMARY_LINE_TEMPLATE = "Summary: {0}";
    private static final String TWEET_INTRO_LINE_TEMPLATE = "Tweet {0} from {1} - {2,date}";
    private static final String NO_TWEETS_FOUND_FOR_REPOSITORY = "No tweets found for this repository.";

    public void print(List<GitHubRepositoryModel> gitHubRepositoryModels) {
        int repositoryCount = 1;
        for (GitHubRepositoryModel repository : gitHubRepositoryModels) {
            System.out.println(MessageFormat.format(GIT_HUB_URL_LINE_TEMPLATE, repositoryCount, repository.getUrl()));
            System.out.println(MessageFormat.format(GIT_HUB_SUMMARY_LINE_TEMPLATE, repository.getDescription()));
            int tweetNr = 1;
            for (TweetModel tweet : repository.getTweets()) {
                System.out.println("----");
                System.out.println(MessageFormat.format(TWEET_INTRO_LINE_TEMPLATE, tweetNr, tweet.getFromUser(), tweet.getCreatedAt()));
                System.out.println(tweet.getText());
                tweetNr++;
            }
            if (repository.getTweets().isEmpty()) {
                System.out.println(NO_TWEETS_FOUND_FOR_REPOSITORY);
            }
            System.out.println("=======================");
            repositoryCount++;
        }
    }
}
