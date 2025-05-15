package com.saga.response.controller;

import com.saga.dto.response.ResponseSuccess;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class BaseController {
    public <T> ResponseEntity<ResponseSuccess<T>> execute(T response, int status) {
        return new ResponseEntity<>(new ResponseSuccess<>(UUID.randomUUID().toString(), status, "Thành công", response), HttpStatusCode.valueOf(status));
    }
}
