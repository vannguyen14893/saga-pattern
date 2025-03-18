package com.saga.orchestration.config;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import com.saga.security.config.SecurityResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class CommonConfig extends CustomGlobalExceptionHandler implements KafkaConfig, SecurityResourceConfig {

}
