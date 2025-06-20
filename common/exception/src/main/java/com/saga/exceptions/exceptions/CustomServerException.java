package com.saga.exceptions.exceptions;
/**
 * Custom exception to represent server-side errors in the application.
 * Extends {@link RuntimeException} to allow unchecked exception handling.
 */
public class CustomServerException extends RuntimeException {
    /**
     * Constructs a new CustomServerException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CustomServerException(String message) {
        super(message);
    }
}