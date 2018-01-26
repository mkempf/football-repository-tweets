package com.sportdec.codingchallenge.apimashup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

@RunWith(SpringRunner.class)
@RestClientTest(GitHubSearchService.class)
@ActiveProfiles("test")
public class GitHubSearchServiceTest {

    @Inject
    private GitHubSearchService gitHubSearchService;

    @Inject
    private MockRestServiceServer server;

    @Test
    public void searchByTopic_resultFound_resultMappedCorrectly() throws Exception {

        this.server.expect(requestTo("https://api.github.com/search/repositories?q=topic:football&sort=stars&order=desc"))
                .andRespond(withSuccess("{ \"items\": [ { \"full_name\": \"repoName\", \"html_url\": \"URL\", \"description\": \"description\"} ]}", MediaType.APPLICATION_JSON));

        GitHubRepositorySearchResult searchResult = gitHubSearchService.searchRepositoryByTopic("football");

        assertEquals(searchResult.getItems().size(), 1);
        assertEquals(searchResult.getItems().get(0).getFullName(),"repoName");
        assertEquals(searchResult.getItems().get(0).getDescription(),"description");
        assertEquals(searchResult.getItems().get(0).getHtmlURL(),"URL");
    }

    @Test
    public void searchByTopic_error_emptyResult() throws Exception {

        this.server.expect(requestTo("https://api.github.com/search/repositories?q=topic:football&sort=stars&order=desc"))
                .andRespond(withServerError());

        GitHubRepositorySearchResult searchResult = gitHubSearchService.searchRepositoryByTopic("football");

        assertEquals(searchResult.getItems().size(), 0);
    }
}
