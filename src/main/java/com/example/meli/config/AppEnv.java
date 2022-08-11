package com.example.meli.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
public class AppEnv {
    @Getter
    @Setter
    @Value("${max_random_precision}")
    private  int maxRandomPrecision;

    @Getter
    @Setter
    @Value("${redis_enabled}")
    private  int redisEnabled;


}
