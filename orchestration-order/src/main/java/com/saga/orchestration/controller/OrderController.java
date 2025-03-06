package com.saga.orchestration.controller;


import com.saga.orchestration.dto.request.CreateOrderRequest;
import com.saga.orchestration.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.saga.grpc.proto.CheckInventoryRequest;
import org.saga.grpc.proto.CheckInventoryResponse;
import org.saga.grpc.proto.InventoryGrpc;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @GrpcClient("inventory")
    private InventoryGrpc.InventoryBlockingStub stub;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequest createOrderRequest) {
        String productIds = createOrderRequest.orderRequests().stream().map(CreateOrderRequest.CreateOrderDetailRequest::productId)
                .map(String::valueOf).collect(Collectors.joining(","));
        CheckInventoryRequest checkInventoryRequest = CheckInventoryRequest.newBuilder().setProductId(productIds).build();
        CheckInventoryResponse checkInventoryResponse = stub.checkInventory(checkInventoryRequest);
        String data = checkInventoryResponse.getData();
        return new ResponseEntity<>(data, HttpStatusCode.valueOf(201));
    }
}
