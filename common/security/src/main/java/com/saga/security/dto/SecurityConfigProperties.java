package com.saga.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Configuration properties class for security settings.
 * This class maps security-related configuration properties from application configuration files.
 * Properties are prefixed with 'security' and include CORS settings and authorization configurations.
 * <p>
 * Properties:
 * - add-mapping: Defines if CORS mapping should be added
 * - allowed-origins: Comma-separated list of allowed origins
 * - allowed-methods: List of allowed HTTP methods
 * - allowed-credentials: Whether credentials are allowed
 * - allowed-headers: List of allowed headers
 * - max-age: Max age of CORS pre-flight response cache
 * - issuer-uri: URI of the token issuer
 * - permit-all: Array of endpoints that permit all access
 */
@Data
@ConfigurationProperties(prefix = "security", ignoreUnknownFields = false)
public class SecurityConfigProperties {
    @JsonProperty("add-mapping")
    private String addMapping;
    @JsonProperty("allowed-origins")
    private String allowedOrigins;
    @JsonProperty("allowed-methods")
    private List<String> allowedMethods;
    @JsonProperty("allowed-credentials")
    private boolean allowCredentials;
    @JsonProperty("allowed-headers")
    private List<String> allowedHeaders;
    @JsonProperty("max-age")
    private long maxAge;
    @JsonProperty("issuer-uri")
    private String issuerUri;
    @JsonProperty("permit-all")
    private String[] permitAll;
}
