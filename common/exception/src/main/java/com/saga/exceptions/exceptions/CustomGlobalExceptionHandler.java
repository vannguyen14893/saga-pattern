package com.saga.exceptions.exceptions;


import com.saga.dto.response.ResponseError;
import com.saga.exceptions.config.DBMessageSourceConfig;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomGlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Tag(name = "Request invalid", description = "Request invalid")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })
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

        return execute(400, map);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ApiResponse(
            responseCode = "400",
            description = "Handle method argument type mis match",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    @Tag(name = "Request invalid", description = "Request invalid")
    public ResponseEntity<ResponseError<String>> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        log.info(ex.getClass().getName());
        String error = ex.getName() + " should be of type " + ex.getRequiredType();
        return execute(400, error);
    }


    @ExceptionHandler({NoHandlerFoundException.class})
    @ApiResponse(
            responseCode = "400",
            description = "Handle method argument not valid",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    @Tag(name = "Request invalid", description = "Request invalid")
    public ResponseEntity<ResponseError<String>> handleNoHandlerFoundException(final NoHandlerFoundException ex) {
        log.info(ex.getClass().getName());
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        return execute(400, error);
    }


    @ExceptionHandler({NotFoundExceptionHandler.class})
    @ApiResponse(
            responseCode = "404",
            description = "Resource not found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public ResponseEntity<ResponseError<String>> userNotFoundExceptionHandler(final NotFoundExceptionHandler ex) {
        log.info(ex.getClass().getName());
        return execute(404, String.format(new DBMessageSourceConfig().getMessages("404"), ex.getMessage()));
    }


    @ExceptionHandler({Exception.class})
    @ApiResponse(
            responseCode = "500",
            description = "Server error",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseError.class)
            )
    )
    public ResponseEntity<ResponseError<String>> exception(final Exception ex) {
        log.info(ex.getMessage());
        return execute(500, new DBMessageSourceConfig().getMessages("500"));
    }

    public <T> ResponseEntity<ResponseError<T>> execute(int status, T message) {
        return new ResponseEntity<>(new ResponseError<T>(UUID.randomUUID().toString(), status, message), HttpStatusCode.valueOf(status));
    }
}

