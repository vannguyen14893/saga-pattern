package com.example.authorzation.exceptions;

/**
 * Custom exception thrown when user details cannot be found in the system.
 * This exception is typically thrown during authentication or user-related operations
 * when requested user information is not available in the database or storage.
 */
public class UserDetailNotFound extends RuntimeException {

    /**
     * Constructs a new UserDetailNotFound with the specified error message.
     *
     * @param msg the detailed message describing why the user details were not found
     */
    public UserDetailNotFound(String msg) {
        super(msg);
    }
}
