package com.bachir.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    @LoadBalanced // This means the request can go to any of the available instance of "Fraud"
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
