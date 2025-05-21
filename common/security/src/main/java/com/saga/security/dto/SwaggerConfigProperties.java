package com.saga.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties class for Swagger/OpenAPI documentation settings.
 * This class maps Swagger-related configuration properties from application configuration files.
 * Properties are prefixed with 'saga.swagger' and include API documentation configurations.
 * <p>
 * Properties:
 * - title: API documentation title
 * - version: API version
 * - description: API description
 * - termsOfService: Terms of service URL
 * - licenseName: Name of the license
 * - licenseUrl: URL of the license
 * - listServer: Array of server URLs
 * - securityScheme: Security scheme name
 * - securityType: Type of security
 * - tokenType: Type of token
 * - bearerFormat: Format of bearer token
 * - enabled: Whether Swagger documentation is enabled
 */
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

