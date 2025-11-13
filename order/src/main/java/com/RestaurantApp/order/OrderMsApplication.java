package com.RestaurantApp.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMsApplication.class, args);
	}

    @Bean
    @LoadBalanced
    public RestTemplate getrestTemplate() {
        return new RestTemplate();
    }
}
