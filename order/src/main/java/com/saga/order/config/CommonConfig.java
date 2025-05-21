package com.saga.order.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines database, Kafka, and exception handling configurations.
 * This class serves as a central configuration point for the Order service.
 */
// Enable Kafka messaging functionality
@EnableKafka
// Mark this class as a Spring configuration class
@Configuration
// Enable JPA auditing with custom auditor awareness
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig extends CustomGlobalExceptionHandler implements DatabaseConfig, KafkaConfig {
}
