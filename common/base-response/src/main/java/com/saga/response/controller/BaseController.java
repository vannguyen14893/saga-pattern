package com.saga.response.controller;

import com.saga.response.dto.ResponseError;
import com.saga.response.dto.ResponseSuccess;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class BaseController {
    public <T> ResponseEntity<ResponseSuccess<T>> execute(T response, int status) {
        return new ResponseEntity<>(new ResponseSuccess<>(UUID.randomUUID().toString(), status, "Thành công", response), HttpStatusCode.valueOf(status));
    }


    public <T> ResponseEntity<ResponseError<T>> execute(int status, T message) {
        return new ResponseEntity<>(new ResponseError<T>(UUID.randomUUID().toString(), status, message), HttpStatusCode.valueOf(status));
    }
}
