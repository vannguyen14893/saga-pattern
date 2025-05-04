package com.saga.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

import java.util.List;
import java.util.stream.Collectors;

public class JwtValidationException extends AuthenticationException {
    private final List<OAuth2Error> errors;

    public JwtValidationException(List<OAuth2Error> errors) {
        super("JWT validation failed: " +
                errors.stream()
                        .map(OAuth2Error::getDescription)
                        .collect(Collectors.joining(", ")));
        this.errors = errors;
    }

    public List<OAuth2Error> getErrors() {
        return errors;
    }
}