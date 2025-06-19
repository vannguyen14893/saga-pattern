package com.saga.notify.config;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Common configuration class that combines Kafka functionality and global exception handling.
 * Enables Kafka message processing through @EnableKafka annotation and implements
 * both exception handling and Kafka configuration interfaces.
 */
@EnableKafka
@Configuration
public class CommonConfig extends CustomGlobalExceptionHandler implements KafkaConfig {
}
