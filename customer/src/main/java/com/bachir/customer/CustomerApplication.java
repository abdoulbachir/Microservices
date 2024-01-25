package com.bachir.customer;

import feign.Capability;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;  This is deprecated; no need to add it. Only need to add dependency in pom.xml

@SpringBootApplication(
        scanBasePackages = {
                "com.bachir.customer",
                "com.bachir.amqp"
        }
)
@EnableFeignClients(
        basePackages = "com.bachir.clients"
)
@PropertySources(
        {
                @PropertySource("classpath:clients-${spring.profiles.active}.properties") // `spring.profiles.active` will be `default` or `kube` based on the profile we're using
        }
)
//@EnableEurekaClient  This is deprecated; no need to add it. Only need to add dependency in pom.xml
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}