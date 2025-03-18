package com.saga.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

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
