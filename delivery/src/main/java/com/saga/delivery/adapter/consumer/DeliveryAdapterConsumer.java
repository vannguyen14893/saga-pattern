package com.saga.delivery.adapter.consumer;

import com.google.gson.Gson;
import com.saga.delivery.adapter.dto.CreateDeliveryAdapterRequest;
import com.saga.delivery.dto.request.CreateDeliveryRequest;
import com.saga.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAdapterConsumer {
    private final DeliveryService deliveryService;

    @KafkaListener(topics = "delivery", groupId = "saga")
    public void create(String payload) {
        CreateDeliveryRequest createDeliveryAdapterRequest = new Gson().fromJson(payload, CreateDeliveryRequest.class);
        deliveryService.create(createDeliveryAdapterRequest);
    }
}
