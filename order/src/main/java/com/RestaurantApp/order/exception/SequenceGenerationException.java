package com.RestaurantApp.order.exception;

public class SequenceGenerationException extends RuntimeException {
    public SequenceGenerationException(String message) {
        super(message);
    }
    
    public SequenceGenerationException(String messagge, Throwable cause) {
        super(messagge, cause);
    }
}
