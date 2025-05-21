package com.saga.database.config;


import com.saga.database.dto.DatabaseConfigProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Configuration class for database connection settings using HikariCP.
 * Provides beans for database configuration and auditing functionality.
 */
@EnableConfigurationProperties({DatabaseConfigProperties.class})
public interface DatabaseConfig {
    /**
     * Creates an AuditorAware bean that provides the current user ID for auditing.
     * Currently returns a default value of 1L.
     *
     * @return AuditorAware instance that provides the user ID
     */
    @Bean
    default AuditorAware<Long> auditorAware() {
        return () -> Optional.of(1L);
    }

    /**
     * Creates a DatabaseConfigProperties bean with default configuration values.
     *
     * @return DatabaseConfigProperties instance with default settings
     */
    @Bean
    default DatabaseConfigProperties databaseConfigProperties() {
        return new DatabaseConfigProperties();
    }

    /**
     * Creates a HikariCP DataSource bean based on the provided configuration properties.
     * Only created if database.enable is true and DatabaseConfigProperties bean exists.
     *
     * @param databaseConfigProperties Configuration properties for the database connection
     * @return Configured HikariDataSource instance
     */
    @Bean
    @ConditionalOnBean(DatabaseConfigProperties.class)
    @ConditionalOnExpression("'${database.enable}'=='true'")
    default DataSource hikariDataSource(DatabaseConfigProperties databaseConfigProperties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseConfigProperties.getUrl());
        hikariConfig.setUsername(databaseConfigProperties.getUsername());
        hikariConfig.setPassword(databaseConfigProperties.getPassword());
        hikariConfig.setDriverClassName(databaseConfigProperties.getDriver());
        hikariConfig.setAutoCommit(databaseConfigProperties.isAutoCommit());
        hikariConfig.setPoolName(databaseConfigProperties.getPoolName());
        hikariConfig.setMaximumPoolSize(databaseConfigProperties.getMaxPoolSize());
        hikariConfig.setMinimumIdle(databaseConfigProperties.getMinPoolSize());
        for (var entry : databaseConfigProperties.getProperties().entrySet()) {
            hikariConfig.addDataSourceProperty(entry.getKey(), entry.getValue());
        }
        return new HikariDataSource(hikariConfig);
    }

}