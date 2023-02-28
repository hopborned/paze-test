package com.example.paze.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paze.client")
public record PazeProperties(
        int connectionTimeout,
        int readTimeout,
        String token,
        String url
) {
}

