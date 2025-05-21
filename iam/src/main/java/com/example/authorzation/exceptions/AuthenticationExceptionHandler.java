package com.example.authorzation.exceptions;

import org.springframework.security.authentication.AccountStatusException;

/**
 * Custom exception handler for authentication-related errors extending Spring Security's AccountStatusException.
 * This class provides specialized handling for authentication failures and account status issues
 * within the authorization system.
 *
 * <p>It allows for both simple message-based exceptions and exceptions with underlying causes
 * through its two constructors.</p>
 */
public class AuthenticationExceptionHandler extends AccountStatusException {

    public AuthenticationExceptionHandler(String msg) {
        super(msg);
    }

    public AuthenticationExceptionHandler(String msg, Throwable cause) {
        super(msg, cause);
    }
}
