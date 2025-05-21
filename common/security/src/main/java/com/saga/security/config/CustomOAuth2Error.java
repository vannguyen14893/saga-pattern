package com.saga.security.config;

import org.springframework.security.oauth2.core.OAuth2Error;

import java.util.Collections;
import java.util.Map;

/**
 * Custom implementation of OAuth2Error that adds support for additional error details.
 * Extends the standard OAuth2Error class to include a map of detailed error information
 * that can be used to provide more context about the OAuth2 authentication/authorization error.
 */
public class CustomOAuth2Error extends OAuth2Error {
    private final Map<String, Object> details;

    public CustomOAuth2Error(String errorCode, String description, Map<String, Object> details) {
        super(errorCode);
        this.details = details != null ? details : Collections.emptyMap();
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
