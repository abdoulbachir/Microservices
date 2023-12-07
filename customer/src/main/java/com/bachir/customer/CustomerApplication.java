package com.bachir.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;  This is deprecated; no need to add it. Only need to add dependency in pom.xml

@SpringBootApplication
@EnableFeignClients(
        basePackages = "com.bachir.clients"
)
//@EnableEurekaClient  This is deprecated; no need to add it. Only need to add dependency in pom.xml
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}