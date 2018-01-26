package com.sportdec.codingchallenge.apimashup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@Profile("!test")
public class FootballRepositoryTweetsRunner implements CommandLineRunner{

    @Inject
    private FootballRepositoryTweets footballRepositoryTweets;

    @Inject
    private GitHubRepositoryTweetPrinter printer;


    @Override
    public void run(String... args) throws Exception {
        printer.print(footballRepositoryTweets.fetch());
    }
}
