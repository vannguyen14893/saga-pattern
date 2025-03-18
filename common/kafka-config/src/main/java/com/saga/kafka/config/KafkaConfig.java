package com.saga.kafka.config;

import com.saga.kafka.dto.KafkaConfigProperties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public interface KafkaConfig {
    @Bean
    default KafkaConfigProperties kafkaConfigProperties() {
        return new KafkaConfigProperties();
    }

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

    @Bean
    default ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory, KafkaConfigProperties kafkaConfigProperties) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setBatchListener(kafkaConfigProperties.isBatchListener());
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

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

    @Bean
    default KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
