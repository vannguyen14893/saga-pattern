package com.saga.response.controller;

import com.saga.dto.response.PaginationResult;
import com.saga.dto.response.ResponseSuccess;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

/**
 * Base controller class providing common response handling functionality for REST endpoints.
 */
public class BaseController {
    /**
     * Creates a ResponseEntity with a ResponseSuccess wrapper for single response objects.
     *
     * @param <T>      the type of response data
     * @param response the response data to be wrapped
     * @param status   the HTTP status code as a string
     * @return ResponseEntity containing the wrapped response
     */
    public <T> ResponseEntity<ResponseSuccess<T>> execute(T response, String status) {
        return new ResponseEntity<>(new ResponseSuccess<>(UUID.randomUUID().toString(), status, "Thành công", response), HttpStatusCode.valueOf(Integer.parseInt(status)));
    }

    /**
     * Creates a ResponseEntity with a PaginationResult wrapper for paginated responses.
     *
     * @param <T>      the type of response data
     * @param response the response data to be wrapped
     * @param total    the total number of items
     * @return ResponseEntity containing the paginated response
     */
    public <T> ResponseEntity<PaginationResult<T>> execute(T response, long total) {
        return new ResponseEntity<>(new PaginationResult<>(UUID.randomUUID().toString(), "200", "Thành công", response, total), HttpStatusCode.valueOf(200));
    }
}
