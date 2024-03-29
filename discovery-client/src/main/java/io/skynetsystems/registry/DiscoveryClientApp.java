package io.skynetsystems.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@RestController
public class DiscoveryClientApp {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
            return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryClientApp.class, args);
    }

}