package com.saga.order.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig extends CustomGlobalExceptionHandler implements DatabaseConfig, KafkaConfig {
}
