package com.sportdec.codingchallenge.apimashup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubTwitterApiMashupApplication {


	public static void main(String[] args) {
		SpringApplication.run(GithubTwitterApiMashupApplication.class, args);
	}

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


	@Bean
	public CommandLineRunner run(GitHubSearchService gitHubSearchService, TwitterSearchService twitterSearchService) {
		return args -> {
			System.out.println(gitHubSearchService.searchRepository("Football"));
			twitterSearchService.search("https://github.com/facebook/react/pull/11818");
		};
	}
}
