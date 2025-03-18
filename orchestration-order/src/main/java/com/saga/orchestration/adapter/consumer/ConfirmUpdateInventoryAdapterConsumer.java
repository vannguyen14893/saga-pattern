package com.saga.orchestration.adapter.consumer;

import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmUpdateInventoryAdapterConsumer {
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    @KafkaListener(topics = "confirm-inventory", groupId = "${kafka.group-id}")
    public void confirmUpdateInventory(String payload) {
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
    }
}
