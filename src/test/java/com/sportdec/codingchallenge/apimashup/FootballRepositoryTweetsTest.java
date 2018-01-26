package com.sportdec.codingchallenge.apimashup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Testing {@link FootballRepositoryTweets}
 */
@RunWith(MockitoJUnitRunner.class)
public class FootballRepositoryTweetsTest {

    @Mock
    private GitHubSearchService gitHubSearchService;

    @Mock
    private TwitterSearchService twitterSearchService;

    private FootballRepositoryTweets footballRepositoryTweets;

    @Before
    public void setUp() {
        footballRepositoryTweets = new FootballRepositoryTweets(gitHubSearchService, twitterSearchService);
    }

    @Test
    public void fetch_NoRepository_EmptyFetch() {
        // given
        when(gitHubSearchService.searchRepositoryByTopic(anyString())).thenReturn(new GitHubRepositorySearchResult());

        //when / then
        assertTrue(footballRepositoryTweets.fetch().isEmpty());
    }

    @Test
    public void fetch_repoFoundNoTweet_ReposWithoutTweetsFetched() {
        // given
        when(gitHubSearchService.searchRepositoryByTopic(anyString())).thenReturn(createGitHubRespositorySearchResult(2));
        when(twitterSearchService.searchTweetsWithUrl(anyString())).thenReturn(new SearchResults(Collections.emptyList(),null));

        // when
        List<GitHubRepositoryModel> model = footballRepositoryTweets.fetch();

        // then
        assertEquals(model.size(), 2);
        assertEquals(model.get(0).getUrl(), "0");
        assertTrue(model.get(0).getTweets().isEmpty());
    }

    @Test
    public void fetch_repoFoundWithTweets_ReposWithTweetsFetched() {
        // given
        when(gitHubSearchService.searchRepositoryByTopic(anyString())).thenReturn(createGitHubRespositorySearchResult(2));
        SearchResults mockSearchResults = createTwitterSearchResult("test");
        when(twitterSearchService.searchTweetsWithUrl(anyString())).thenReturn(mockSearchResults);

        // when
        List<GitHubRepositoryModel> model = footballRepositoryTweets.fetch();

        // then
        assertEquals(model.size(), 2);
        assertEquals(model.get(0).getUrl(), "0");
        assertFalse(model.get(0).getTweets().isEmpty());
        assertEquals(model.get(0).getTweets().get(0).getFromUser(),"test");
    }

    private SearchResults createTwitterSearchResult(String user) {
        Tweet mockTweet = Mockito.mock(Tweet.class);
        when(mockTweet.getFromUser()).thenReturn(user);
        return new SearchResults(new ArrayList<Tweet>(){{ add(mockTweet);}}, null);
    }

    private GitHubRepositorySearchResult createGitHubRespositorySearchResult(int number) {
        GitHubRepositorySearchResult searchResult = new GitHubRepositorySearchResult();
        List<GitHubRepository> repositories = IntStream
                .range(0, number)
                .mapToObj(nbr -> {
                    GitHubRepository repo = new GitHubRepository();
                    repo.setHtmlURL(String.valueOf(nbr));
                    return repo;
                })
                .collect(Collectors.toList());
        searchResult.setItems(repositories);
        return searchResult;
    }
}