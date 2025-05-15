package com.saga.product.config;

import com.saga.database.config.DatabaseConfig;
import com.saga.kafka.config.KafkaConfig;
import com.saga.security.config.SecurityResourceConfig;
import com.saga.security.config.SwaggerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class CommonConfig extends SwaggerConfig implements DatabaseConfig, KafkaConfig, SecurityResourceConfig {
}
