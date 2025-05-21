package com.saga.orchestration.adapter.consumer;

import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import com.saga.orchestration.adapter.producer.UpdateInventoryAdapterProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Consumer adapter that handles order confirmation events from Kafka.
 * Acts as part of the saga pattern to coordinate order processing steps.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmCreateOrderAdapterConsumer {
    private final UpdateInventoryAdapterProducer updateInventoryAdapterProducer;
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    /**
     * Listens for order confirmation events and triggers inventory update and outbox event creation.
     *
     * @param payload The order confirmation event data in String format
     */
    @KafkaListener(topics = "confirm-order", groupId = "${kafka.group-id}")
    public void confirmCreateOrder(String payload) {
        updateInventoryAdapterProducer.updateInventory(payload);
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
    }
}
