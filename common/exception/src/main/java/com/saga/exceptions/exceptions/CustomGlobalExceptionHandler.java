package com.saga.exceptions.exceptions;


import com.saga.dto.response.ResponseError;
import com.saga.exceptions.config.DBMessageSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Global exception handler for REST endpoints
 * Provides centralized exception handling across all @RequestMapping methods
 */
@Slf4j
@RestControllerAdvice
public class CustomGlobalExceptionHandler {
    /**
     * Handles validation errors for request body parameters
     *
     * @param ex The MethodArgumentNotValidException
     * @return ResponseEntity with validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError<Map<String, Object>>> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex) {
        Map<String, Object> map = new HashMap<>();
        Set<String> fields = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getField).collect(Collectors.toSet());
        for (String filed : fields) {
            Set<String> errors = new HashSet<>();
            for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
                if (filed.equals(error.getField())) {
                    errors.add(new DBMessageSourceConfig().getMessages(error.getDefaultMessage()));
                }
            }
            map.put(filed, errors);
        }

        return execute("400", map);
    }

    /**
     * Handles type mismatch errors for request parameters
     *
     * @param ex The MethodArgumentTypeMismatchException
     * @return ResponseEntity with error message
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ResponseError<String>> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        log.info(ex.getClass().getName());
        String error = ex.getName() + " should be of type " + ex.getRequiredType();
        return execute("400", error);
    }

    /**
     * Handles unsupported HTTP method errors
     *
     * @param ex The HttpRequestMethodNotSupportedException
     * @return ResponseEntity with error message
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ResponseError<String>> handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        log.info(ex.getClass().getName());
        return execute("405", ex.getMessage());
    }

    /**
     * Handles requests for non-existent endpoints
     *
     * @param ex The NoHandlerFoundException
     * @return ResponseEntity with error message
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<ResponseError<String>> handleNoHandlerFoundException(final NoHandlerFoundException ex) {
        log.info(ex.getClass().getName());
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        return execute("400", error);
    }

    /**
     * Handles not found resource errors
     *
     * @param ex The NotFoundExceptionHandler
     * @return ResponseEntity with error message
     */
    @ExceptionHandler({NotFoundExceptionHandler.class})
    public ResponseEntity<ResponseError<String>> userNotFoundExceptionHandler(final NotFoundExceptionHandler ex) {
        log.info(ex.getClass().getName());
        return execute("404", String.format(new DBMessageSourceConfig().getMessages("404"), ex.getMessage()));
    }

    /**
     * Handles unsupported media type errors
     *
     * @param ex The HttpMediaTypeNotSupportedException
     * @return ResponseEntity with error message
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ResponseError<String>> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex) {
        String error = "Unsupported Media Type" +
                ex.getContentType() + " media type is not supported. Supported media types are: " +
                ex.getSupportedMediaTypes();
        return execute("415", error);
    }

    /**
     * Handles all other unhandled exceptions
     *
     * @param ex The Exception
     * @return ResponseEntity with generic error message
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseError<String>> exception(final Exception ex) {
        log.info(ex.getMessage());
        return execute("500", new DBMessageSourceConfig().getMessages("500"));
    }

    /**
     * Helper method to create ResponseEntity with error details
     *
     * @param status  HTTP status code as string
     * @param message Error message or details
     * @return ResponseEntity containing error information
     */
    public <T> ResponseEntity<ResponseError<T>> execute(String status, T message) {
        return new ResponseEntity<>(new ResponseError<T>(UUID.randomUUID().toString(), status, message), HttpStatusCode.valueOf(200));
    }
}