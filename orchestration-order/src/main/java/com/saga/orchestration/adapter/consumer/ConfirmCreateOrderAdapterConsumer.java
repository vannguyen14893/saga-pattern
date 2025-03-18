package com.saga.orchestration.adapter.consumer;

import com.saga.orchestration.adapter.producer.CreateOutboxEventAdapterProducer;
import com.saga.orchestration.adapter.producer.UpdateInventoryAdapterProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfirmCreateOrderAdapterConsumer {
    private final UpdateInventoryAdapterProducer updateInventoryAdapterProducer;
    private final CreateOutboxEventAdapterProducer createOutboxEventAdapterProducer;

    @KafkaListener(topics = "confirm-order", groupId = "${kafka.group-id}")
    public void confirmCreateOrder(String payload) {
        updateInventoryAdapterProducer.updateInventory(payload);
        createOutboxEventAdapterProducer.createOutboxEvent(payload);
    }
}
