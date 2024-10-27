package com.microservices.AccountService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ChangeDataMapConfig {
    @Bean
     ConcurrentHashMap concurrentHashMap(){
        return new ConcurrentHashMap();
    }
}
