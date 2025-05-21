package com.saga.admin.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.kafka.config.KafkaConfig;
import com.saga.security.config.SecurityResourceConfig;
import com.saga.security.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines various configuration aspects of the application.
 * This class integrates Swagger, Database, Kafka, and Security configurations into a single configuration unit.
 */
@EnableKafka // Enables Kafka message processing capabilities
@Configuration
// Indicates that this class declares one or more @Bean methods and may be processed by the Spring container
@EnableJpaAuditing(auditorAwareRef = "auditorAware") // Enables JPA auditing with custom auditor aware implementation
public class CommonConfig extends SwaggerConfig implements DatabaseConfig, KafkaConfig, SecurityResourceConfig {
}
