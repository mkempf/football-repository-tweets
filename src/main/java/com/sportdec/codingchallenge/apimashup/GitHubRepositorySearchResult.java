package com.sportdec.codingchallenge.apimashup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Search result containing the found {@link GitHubRepository}.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepositorySearchResult {

    private List<GitHubRepository> items = new ArrayList<>();

    public List<GitHubRepository> getItems() {
        return items;
    }

    public void setItems(List<GitHubRepository> items) {
        this.items = items;
    }
}
