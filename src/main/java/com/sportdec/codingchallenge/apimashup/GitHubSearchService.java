package com.sportdec.codingchallenge.apimashup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@Service
public class GitHubSearchService {

    private static final String API_PATH = "https://api.github.com/search/repositories?q={query}";

    private static Logger LOGGER = LoggerFactory.getLogger(GitHubSearchService.class);

    @Inject
    private RestTemplate restTemplate;

    public String searchRepository(String query) {

        LOGGER.debug("API Path: {}", API_PATH);
        try {
            return restTemplate.getForObject(API_PATH, String.class, query);
        } catch (RestClientException e) {
            LOGGER.debug(e.getMessage());
            return "";
        }
    }
}
