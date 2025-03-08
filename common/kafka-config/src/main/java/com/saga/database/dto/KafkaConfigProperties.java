package com.saga.database.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "kafka", ignoreUnknownFields = false)
@Data
@Validated
public class KafkaConfigProperties {
    @NotBlank
    private String bootStrapServer;
    private String groupId;
    private Map<String, String> properties;
}
