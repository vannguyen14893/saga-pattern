package com.saga.database.config;


import com.saga.database.dto.DatabaseConfigProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
@EnableConfigurationProperties({DatabaseConfigProperties.class})

public interface DatabaseConfig {
    @Bean
    default DatabaseConfigProperties databaseConfigProperties() {
        return new DatabaseConfigProperties();
    }
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
