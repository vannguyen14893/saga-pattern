package com.saga.admin.adapter.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.service.outboxevent.OutBoxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Kafka consumer adapter responsible for processing and creating outbox events.
 * This service listens to the outbox-event topic and processes incoming messages in batch mode.
 */
@RequiredArgsConstructor
@Service
public class CreateOutboxEventAdapterConsumer {
    private final OutBoxEventService outBoxEventService;

    /**
     * Processes incoming Kafka messages and creates outbox events in batch.
     *
     * @param records List of JSON string records representing OutboxEvent objects
     * @throws JsonProcessingException if there's an error parsing the JSON records
     */
    @KafkaListener(topics = "outbox-event", groupId = "${kafka.group-id}",batch = "true")
    public void create(List<String> records) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OutboxEvent> outboxEvents = objectMapper.readValue(records.toString(), new TypeReference<>() {});
        outBoxEventService.createMulti(outboxEvents);
    }
}
