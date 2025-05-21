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

/**
 * REST controller that handles order-related operations in the saga orchestration pattern.
 * Coordinates the order creation process and inventory verification using gRPC communication.
 * Part of the distributed transaction management system.
 */
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final InventoryGrpcClientService inventoryGrpcClientService;

    /**
     * Creates a new order after verifying inventory availability.
     * First checks inventory via gRPC, then proceeds with order creation if inventory is available.
     *
     * @param createOrderRequest The order creation request containing order details
     * @return ResponseEntity with status 200 and inventory message if inventory check fails,
     * or status 201 and order creation result if successful
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateOrderRequest createOrderRequest) {
        String data = inventoryGrpcClientService.checkInventory(createOrderRequest);
        if (Strings.isNotBlank(data)) {
            return new ResponseEntity<>(data, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatusCode.valueOf(201));
    }
}
