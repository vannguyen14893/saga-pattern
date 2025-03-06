package com.saga.order.adapter.producer;

import com.google.gson.Gson;
import com.saga.order.dto.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfirmCreateOrderAdapterProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void confirmCreateOrder(List<CreateOrderRequest.CreateOrderDetailRequest> createOrderDetailRequests) {
        String payload = new Gson().toJson(createOrderDetailRequests);
        kafkaTemplate.send("confirm-order", payload);
    }
}
