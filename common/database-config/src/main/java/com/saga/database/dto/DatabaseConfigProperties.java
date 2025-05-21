package com.saga.database.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import java.util.Map;

/**
 * Configuration properties class for database connection settings.
 * This class maps database configuration properties from application configuration files
 * using the 'database' prefix. It includes essential database connection parameters
 * such as URL, credentials, connection pool settings, and additional properties.
 * The class is validated to ensure required properties are provided.
 */
@ConfigurationProperties(prefix = "database", ignoreUnknownFields = false)
@Data
@Validated
public class DatabaseConfigProperties {
    /**
     * Flag to enable/disable the database configuration.
     * Defaults to true.
     */
    private boolean enable = true;
    /**
     * Database connection URL.
     * Must not be blank.
     */
    @NotBlank
    private String url;
    /**
     * Database username for authentication.
     * Must not be blank.
     */
    @NotBlank
    private String username;
    /**
     * Database password for authentication.
     * Must not be blank.
     */
    @NotBlank
    private String password;
    /**
     * Database driver class name.
     * Must not be blank.
     */
    @NotBlank
    private String driver;
    /**
     * Database type identifier.
     * Must not be blank.
     */
    @NotBlank
    private String type;
    /**
     * Flag to enable/disable auto-commit mode for database transactions.
     */
    @JsonProperty("auto-commit")
    private boolean autoCommit;
    /**
     * Name of the connection pool.
     */
    @JsonProperty("pool-name")
    private String poolName;
    /**
     * Maximum number of connections in the pool.
     */
    @JsonProperty("max-pool-size")
    private int maxPoolSize;
    /**
     * Minimum number of idle connections in the pool.
     */
    @JsonProperty("min-pool-size")
    private int minPoolSize;
    /**
     * Additional database properties as key-value pairs.
     */
    private Map<String, String> properties;
}
