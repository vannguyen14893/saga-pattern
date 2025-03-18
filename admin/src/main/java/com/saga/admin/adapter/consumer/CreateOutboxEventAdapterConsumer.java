package com.saga.admin.adapter.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.service.OutBoxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateOutboxEventAdapterConsumer {
    private final OutBoxEventService outBoxEventService;

    @KafkaListener(topics = "outbox-event", groupId = "${kafka.group-id}",batch = "true")
    public void create(List<String> records) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<OutboxEvent> outboxEvents = objectMapper.readValue(records.toString(), new TypeReference<>() {
        });
        outBoxEventService.createMulti(outboxEvents);
    }
}
