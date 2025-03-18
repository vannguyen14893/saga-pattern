package com.saga.orchestration.controller;


import com.saga.dto.request.CreateOrderRequest;
import com.saga.orchestration.grpc.InventoryGrpcClientService;
import com.saga.orchestration.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final InventoryGrpcClientService inventoryGrpcClientService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequest createOrderRequest) {
        String data = inventoryGrpcClientService.checkInventory(createOrderRequest);
        if (Strings.isNotBlank(data)) {
            return new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatusCode.valueOf(201));
    }
}
