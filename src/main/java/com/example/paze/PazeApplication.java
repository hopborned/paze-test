package com.example.paze;

import com.example.paze.properties.PazeProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PazeProperties.class)
public class PazeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PazeApplication.class, args);
    }

}
