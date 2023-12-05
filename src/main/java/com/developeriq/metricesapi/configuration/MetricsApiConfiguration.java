package com.developeriq.metricesapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class MetricsApiConfiguration {
    @Value("${github.base.url}")
    private String gitHubBaseUrl;
    @Autowired
    WebClient.Builder webCliBuilder;

    @Bean("gitHubclient")
    public WebClient getGitHubWebClient() {
        return webCliBuilder.baseUrl(gitHubBaseUrl).build();
    }

}
