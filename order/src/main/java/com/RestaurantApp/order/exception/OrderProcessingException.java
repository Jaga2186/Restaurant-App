package com.RestaurantApp.order.exception;

public class OrderProcessingException extends RuntimeException{
    public OrderProcessingException(String message) {
        super(message);
    }
    public OrderProcessingException(String message, Exception cause) {
        super(message, cause);
    }
}
