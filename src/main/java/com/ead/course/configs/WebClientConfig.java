package com.ead.course.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Value("${ead.api.url.authuser}")
    private String AUTHUSER_BASE_URL;

    @Bean
    public WebClient authUserService(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(AUTHUSER_BASE_URL).build();
    }

}
