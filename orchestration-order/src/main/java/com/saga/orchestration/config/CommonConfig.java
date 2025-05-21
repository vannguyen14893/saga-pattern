package com.saga.orchestration.config;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import com.saga.security.config.SecurityResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines Kafka, security, and exception handling configurations.
 * Enables Kafka messaging functionality and implements necessary configuration interfaces.
 * Acts as a central configuration hub for the orchestration service.
 */
@EnableKafka
@Configuration
public class CommonConfig extends CustomGlobalExceptionHandler implements KafkaConfig, SecurityResourceConfig {

}
