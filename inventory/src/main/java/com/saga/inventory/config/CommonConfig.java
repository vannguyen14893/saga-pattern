package com.saga.inventory.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines database and Kafka configurations.
 * Enables Kafka message processing with @EnableKafka.
 * Enables JPA auditing with custom auditor aware implementation.
 * Implements database and Kafka specific configurations through respective interfaces.
 */
@EnableKafka
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig implements DatabaseConfig, KafkaConfig {
}
