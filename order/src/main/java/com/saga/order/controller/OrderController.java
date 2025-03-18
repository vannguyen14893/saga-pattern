package com.saga.order.controller;

import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.dto.request.UpdateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.service.OrderService;
import com.saga.response.controller.BaseController;
import com.saga.response.dto.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController extends BaseController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<Order>>> findAll() {
        return execute(orderService.findAll(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Order>> findById(@PathVariable String id) {
        return execute(orderService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<Order>> create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return execute(orderService.create(createOrderRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<Order>> update(@RequestBody @Valid UpdateOrderRequest updateOrderRequest) {
        return execute(orderService.update(updateOrderRequest), 20);
    }

    @DeleteMapping("/{id}/{status}")
    public ResponseEntity<ResponseSuccess<String>> deleteById(@PathVariable String id, @PathVariable String status) {
        return execute(orderService.updateStatus(id, status), 200);
    }
}
