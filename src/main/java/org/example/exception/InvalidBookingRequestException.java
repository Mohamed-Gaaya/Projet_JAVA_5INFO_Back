package org.example.exception;

public class InvalidBookingRequestException extends RuntimeException {
    public InvalidBookingRequestException(String message) {
        super(message);
    }
}
