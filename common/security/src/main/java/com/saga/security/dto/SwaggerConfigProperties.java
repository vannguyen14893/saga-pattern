package com.saga.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "saga.swagger", ignoreUnknownFields = false)
public class SwaggerConfigProperties {
    private String title = "API";
    private String version = "1.0";
    private String description;
    private String termsOfService = "http://localhost:8088";
    @JsonProperty("license-name")
    private String licenseName = "saga";
    @JsonProperty("license-url")
    private String licenseUrl = "http://localhost:8088";
    @JsonProperty("list-server")
    private String[] listServer;
    private String securityScheme = "bearerAuth";
    private String securityType = "http";
    @JsonProperty("token-type")
    private String tokenType = "bearer";
    private String bearerFormat = "JWT";
    private boolean enabled ;
}

