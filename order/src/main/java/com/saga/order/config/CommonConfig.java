package com.saga.order.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.response.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class CommonConfig implements DatabaseConfig, KafkaConfig {
}
