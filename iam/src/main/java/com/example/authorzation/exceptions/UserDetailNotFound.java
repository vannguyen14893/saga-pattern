package com.example.authorzation.exceptions;

public class UserDetailNotFound extends RuntimeException {

    public UserDetailNotFound(String msg) {
        super(msg);
    }
}
