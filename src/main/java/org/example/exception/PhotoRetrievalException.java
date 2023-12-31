package org.example.exception;

public class PhotoRetrievalException extends RuntimeException {
    public PhotoRetrievalException(String message) {
        super(message);
    }
}
