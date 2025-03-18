package com.saga.product.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig implements DatabaseConfig, KafkaConfig {
}
