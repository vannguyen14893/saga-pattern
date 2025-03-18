package com.saga.notify.config;

import com.saga.exceptions.exceptions.CustomGlobalExceptionHandler;
import com.saga.kafka.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class CommonConfig extends CustomGlobalExceptionHandler implements KafkaConfig {
}
