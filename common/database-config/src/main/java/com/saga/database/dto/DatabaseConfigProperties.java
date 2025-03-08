package com.saga.database.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import java.util.Map;
@ConfigurationProperties(prefix = "database", ignoreUnknownFields = false)
@Data
@Validated
public class DatabaseConfigProperties {
    private boolean enable = true;
    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String driver;
    @NotBlank
    private String type;
    @JsonProperty("auto-commit")
    private boolean autoCommit;
    @JsonProperty("pool-name")
    private String poolName;
    @JsonProperty("max-pool-size")
    private int maxPoolSize;
    @JsonProperty("min-pool-size")
    private int minPoolSize;
    private Map<String, String> properties;
}
