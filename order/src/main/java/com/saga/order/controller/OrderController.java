package com.saga.order.controller;

import com.saga.dto.response.ResponseSuccess;
import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.dto.request.UpdateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.service.OrderService;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Order operations.
 * Handles HTTP requests for creating, reading, updating, and deleting orders.
 */
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController extends BaseController {
    private final OrderService orderService;

    /**
     * Retrieves all orders from the system.
     *
     * @return ResponseEntity containing a list of all orders
     */
    @GetMapping
    public ResponseEntity<ResponseSuccess<List<Order>>> findAll() {
        return execute(orderService.findAll(), "200");
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id the ID of the order to retrieve
     * @return ResponseEntity containing the requested order
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Order>> findById(@PathVariable String id) {
        return execute(orderService.findById(id), "200");
    }

    /**
     * Creates a new order in the system.
     *
     * @param createOrderRequest the order creation request containing order details
     * @return ResponseEntity containing the created order
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<Order>> create(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        return execute(orderService.create(createOrderRequest), "201");
    }

    /**
     * Updates an existing order in the system.
     *
     * @param updateOrderRequest the order update request containing updated order details
     * @return ResponseEntity containing the updated order
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<Order>> update(@RequestBody @Valid UpdateOrderRequest updateOrderRequest) {
        return execute(orderService.update(updateOrderRequest), "200");
    }

    /**
     * Updates the status of an order.
     *
     * @param id     the ID of the order to update
     * @param status the new status to set
     * @return ResponseEntity containing the result message
     */
    @DeleteMapping("/{id}/{status}")
    public ResponseEntity<ResponseSuccess<String>> deleteById(@PathVariable String id, @PathVariable String status) {
        return execute(orderService.updateStatus(id, status), "200");
    }
}
