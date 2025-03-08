package com.saga.exceptions.exceptions;


import com.saga.exceptions.config.DBMessageSourceConfig;
import com.saga.response.controller.BaseController;
import com.saga.response.dto.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomGlobalExceptionHandler extends BaseController {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<ResponseError<String>> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex) {
        log.info(ex.getClass().getName());
        String error = ex.getName() + " should be of type " + ex.getRequiredType();
        return execute(400, error);
    }


    @ExceptionHandler({NoHandlerFoundException.class})
    protected ResponseEntity<ResponseError<String>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.info(ex.getClass().getName());
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        return execute(400, error);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<ResponseError<Map<String, Object>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
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

    @ExceptionHandler({NotFoundExceptionHandler.class})
    protected ResponseEntity<ResponseError<String>> userNotFoundExceptionHandler(final NotFoundExceptionHandler ex) {
        log.info(ex.getClass().getName());
        return execute(404, String.format(new DBMessageSourceConfig().getMessages("404"), ex.getMessage()));
    }


    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ResponseError<String>> exception(final Exception ex) {
        log.info(ex.getMessage());
        return execute(500, new DBMessageSourceConfig().getMessages("500"));
    }

}

