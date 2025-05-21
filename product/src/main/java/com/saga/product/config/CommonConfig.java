package com.saga.product.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.kafka.config.KafkaConfig;
import com.saga.security.config.SecurityResourceConfig;
import com.saga.security.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines multiple configuration aspects of the application.
 * This class enables and configures:
 * - Kafka messaging (@EnableKafka)
 * - JPA auditing with custom auditor awareness
 * - Database configuration
 * - Security resource settings
 * - Swagger documentation
 */
@EnableKafka
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig extends SwaggerConfig implements DatabaseConfig, KafkaConfig, SecurityResourceConfig {
}
