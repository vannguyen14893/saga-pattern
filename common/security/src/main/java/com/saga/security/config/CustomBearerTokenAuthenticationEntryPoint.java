package com.saga.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.dto.response.ResponseError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Custom implementation of AuthenticationEntryPoint for handling Bearer token authentication failures.
 * Provides JSON formatted error responses for unauthorized access attempts and JWT validation failures.
 */
public class CustomBearerTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Handles authentication failures by returning a JSON formatted error response.
     * For JWT validation failures, includes detailed validation errors in the response.
     *
     * @param request       The HTTP request that resulted in an AuthenticationException
     * @param response      The HTTP response to be modified
     * @param authException The exception that caused the invocation
     * @throws IOException If an I/O error occurs while writing the response
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Object message;
        ResponseError<?> responseError;
        if (authException instanceof JwtValidationException) {
            JwtValidationException jwtEx = (JwtValidationException) authException;
            List<Map<String, Object>> validationErrors = jwtEx.getErrors().stream()
                    .map(error -> {
                        Map<String, Object> errorMap = new LinkedHashMap<>();
                        errorMap.put("code", error.getErrorCode());
                        errorMap.put("description", error.getDescription());
                        if (error instanceof CustomOAuth2Error) {
                            errorMap.put("details", ((CustomOAuth2Error) error).getDetails());
                        }
                        return errorMap;
                    })
                    .collect(Collectors.toList());
            message = validationErrors;
        } else {
            message = authException.getMessage();
        }
        responseError = new ResponseError<>(UUID.randomUUID().toString(), String.valueOf(HttpStatus.UNAUTHORIZED.value()), message);

        new ObjectMapper().writeValue(response.getOutputStream(), responseError);
    }
}