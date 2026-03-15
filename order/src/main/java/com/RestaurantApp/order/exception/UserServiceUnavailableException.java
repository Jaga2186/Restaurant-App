package com.RestaurantApp.order.exception;

public class UserServiceUnavailableException extends RuntimeException {
    public UserServiceUnavailableException(String message) {
        super(message);
    }

    public UserServiceUnavailableException(String message, Exception cause) {
        super(message, cause);
    }
    
}
