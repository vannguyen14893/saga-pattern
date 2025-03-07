package com.saga.order.adapter.producer;

import com.google.gson.Gson;
import com.saga.order.dto.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmCreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void confirmCreateOrder(CreateOrderRequest createOrderRequest) {
        String payload = new Gson().toJson(createOrderRequest);
        kafkaTemplate.send("confirm-order", payload);
    }
}
