package com.example.authorzation.exceptions;

import org.springframework.security.authentication.AccountStatusException;

public class AccountStatusExceptionHandler extends AccountStatusException {

    public AccountStatusExceptionHandler(String msg) {
        super(msg);
    }

    public AccountStatusExceptionHandler(String msg, Throwable cause) {
        super(msg, cause);
    }
    {}
}
