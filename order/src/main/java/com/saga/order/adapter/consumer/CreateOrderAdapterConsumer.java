package com.saga.order.adapter.consumer;

import com.google.gson.Gson;
import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer adapter for handling order creation requests.
 * Listens to the order topic and processes incoming order creation messages.
 */
@Service
@RequiredArgsConstructor
public class CreateOrderAdapterConsumer {
    private final OrderService orderService;

    /**
     * Processes incoming Kafka messages for order creation.
     * Converts JSON payload to CreateOrderRequest and delegates to OrderService.
     *
     * @param payload JSON string containing order creation details
     * @throws RuntimeException if the payload is blank
     */
    @KafkaListener(topics = "order", groupId = "${kafka.group-id}")
    public void create(String payload) {
        if (Strings.isNotBlank(payload)) throw new RuntimeException("ERROR");
        CreateOrderRequest createOrderRequest = new Gson().fromJson(payload, CreateOrderRequest.class);
        orderService.create(createOrderRequest);
    }

}
