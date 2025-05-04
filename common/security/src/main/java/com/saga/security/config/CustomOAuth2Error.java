package com.saga.security.config;

import org.springframework.security.oauth2.core.OAuth2Error;

import java.util.Collections;
import java.util.Map;

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
