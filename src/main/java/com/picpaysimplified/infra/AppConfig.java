package com.picpaysimplified.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//    Config the RestTemplate on the classes that are dependent on it
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
