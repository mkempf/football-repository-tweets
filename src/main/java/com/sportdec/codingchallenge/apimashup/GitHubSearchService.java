package com.sportdec.codingchallenge.apimashup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Service to search git hub repositories using the <a href="https://developer.github.com/v3/search/#search-repositories">gitHub API.</a>
 */
@Service
public class GitHubSearchService {

    private static final String API_PATH = "https://api.github.com/search/repositories?q=topic:{topic}&sort=stars&order=desc";

    private static Logger LOGGER = LoggerFactory.getLogger(GitHubSearchService.class);

    @Inject
    private RestTemplate restTemplate;

    public GitHubRepositorySearchResult searchRepositoryByTopic(String topic) {
        LOGGER.debug("Search GitHub repository with topic: {}", topic);
        try {
            return restTemplate.getForObject(API_PATH, GitHubRepositorySearchResult.class, topic);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new GitHubRepositorySearchResult();
        }
    }
}
