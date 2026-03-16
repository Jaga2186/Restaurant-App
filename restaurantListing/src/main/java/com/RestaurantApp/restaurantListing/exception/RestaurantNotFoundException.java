package com.RestaurantApp.restaurantListing.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Integer id) {
        super("Restaurant with ID " + id + " not found.");
    }
}
