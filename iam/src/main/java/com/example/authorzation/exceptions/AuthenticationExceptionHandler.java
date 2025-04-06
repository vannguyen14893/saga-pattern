package com.example.authorzation.exceptions;

import org.springframework.security.authentication.AccountStatusException;

public class AuthenticationExceptionHandler extends AccountStatusException {

    public AuthenticationExceptionHandler(String msg) {
        super(msg);
    }

    public AuthenticationExceptionHandler(String msg, Throwable cause) {
        super(msg, cause);
    }
    {}
}
