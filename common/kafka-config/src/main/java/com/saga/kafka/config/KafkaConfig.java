package com.saga.kafka.config;

import com.saga.kafka.dto.KafkaConfigProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka settings, providing beans for Kafka producers and consumers.
 * This interface defines the necessary configurations for Kafka integration in the application.
 */
@Configuration
public interface KafkaConfig {
    /**
     * Creates a KafkaConfigProperties bean with default configuration settings.
     *
     * @return KafkaConfigProperties instance with default settings
     */
    @Bean
    default KafkaConfigProperties kafkaConfigProperties() {
        return new KafkaConfigProperties();
    }

    /**
     * Creates a Kafka ConsumerFactory with specified configuration properties.
     *
     * @param kafkaConfigProperties the configuration properties for Kafka
     * @return ConsumerFactory instance configured for String key-value pairs
     */
    @Bean
    default ConsumerFactory<String, String> consumerFactory(KafkaConfigProperties kafkaConfigProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootStrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfigProperties.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        for (var entry : kafkaConfigProperties.getPropertiesProducer().entrySet()) {
            props.putIfAbsent(entry.getKey(), entry.getValue());
        }
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * Creates a KafkaListenerContainerFactory for handling Kafka message listening.
     *
     * @param consumerFactory       the consumer factory to be used
     * @param kafkaConfigProperties the configuration properties for Kafka
     * @return ConcurrentKafkaListenerContainerFactory configured for String key-value pairs
     */
    @Bean
    default ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory, KafkaConfigProperties kafkaConfigProperties) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setBatchListener(kafkaConfigProperties.isBatchListener());
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    /**
     * Creates a Kafka ProducerFactory with specified configuration properties.
     *
     * @param kafkaConfigProperties the configuration properties for Kafka
     * @return ProducerFactory instance configured for String key-value pairs
     */
    @Bean
    default ProducerFactory<String, String> producerFactory(KafkaConfigProperties kafkaConfigProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigProperties.getBootStrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        for (var entry : kafkaConfigProperties.getPropertiesProducer().entrySet()) {
            props.putIfAbsent(entry.getKey(), entry.getValue());
        }
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * Creates a KafkaTemplate for sending messages to Kafka topics.
     *
     * @param producerFactory the producer factory to be used
     * @return KafkaTemplate configured for String key-value pairs
     */
    @Bean
    default KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
