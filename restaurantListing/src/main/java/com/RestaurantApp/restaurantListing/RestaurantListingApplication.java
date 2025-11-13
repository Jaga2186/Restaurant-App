package com.RestaurantApp.restaurantListing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestaurantListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantListingApplication.class, args);
	}

}
