package com.example.paze.configuration;

import com.example.paze.properties.PazeProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(PazeProperties properties, RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofSeconds(properties.readTimeout()))
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.token())
                .build();
    }

}
