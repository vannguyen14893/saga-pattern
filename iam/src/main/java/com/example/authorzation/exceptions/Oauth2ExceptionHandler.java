package com.example.authorzation.exceptions;

import org.springframework.security.core.AuthenticationException;

public class Oauth2ExceptionHandler extends AuthenticationException {
    public Oauth2ExceptionHandler(String msg, Throwable cause) {
        super(msg, cause);
    }

    public Oauth2ExceptionHandler(String msg) {
        super(msg);
    }
}
