package com.saga.order.controller;

import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.dto.request.UpdateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<>(orderService.list(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable String id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest createOrderRequest) {
        return new ResponseEntity<>(orderService.create(createOrderRequest), HttpStatusCode.valueOf(201));
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody UpdateOrderRequest updateOrderRequest) {
        return new ResponseEntity<>(orderService.update(updateOrderRequest), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}/{status}")
    public ResponseEntity<String> findById(@PathVariable String id, @PathVariable String status) {
        return new ResponseEntity<>(orderService.updateStatus(id, status), HttpStatusCode.valueOf(200));
    }
}
