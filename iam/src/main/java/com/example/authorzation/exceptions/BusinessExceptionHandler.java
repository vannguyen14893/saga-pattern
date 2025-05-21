package com.example.authorzation.exceptions;

/**
 * Custom exception handler for business-related errors extending RuntimeException.
 * This class provides specialized handling for business logic failures and validation issues
 * within the application.
 *
 * <p>It encapsulates business rule violations and operational errors that occur during
 * normal application execution.</p>
 */
public class BusinessExceptionHandler extends RuntimeException {

    /**
     * Constructs a new BusinessExceptionHandler with the specified error message.
     *
     * @param msg the detailed message describing the business rule violation or error condition
     */
    public BusinessExceptionHandler(String msg) {
        super(msg);
    }
}
