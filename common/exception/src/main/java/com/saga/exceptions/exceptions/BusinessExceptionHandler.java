package com.saga.exceptions.exceptions;

/**
 * Exception handler for resource not found scenarios.
 * This runtime exception is thrown when a requested resource cannot be found in the system.
 */
public class BusinessExceptionHandler extends RuntimeException {

    /**
     * Constructs a new BusinessExceptionHandler with the specified error message.
     *
     * @param msg the detail message explaining the not found condition
     */
    public BusinessExceptionHandler(String msg) {
        super(msg);
    }
}
