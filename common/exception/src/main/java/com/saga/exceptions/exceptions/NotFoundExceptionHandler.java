package com.saga.exceptions.exceptions;

public class NotFoundExceptionHandler extends RuntimeException {

    public NotFoundExceptionHandler(String msg) {
        super(msg);
    }
}
