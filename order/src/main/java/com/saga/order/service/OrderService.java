package com.saga.order.service;

import com.saga.order.adapter.producer.ConfirmCreateOrderAdapterProducer;
import com.saga.order.dto.request.CreateOrderRequest;
import com.saga.order.dto.request.UpdateOrderRequest;
import com.saga.order.entity.Order;
import com.saga.order.entity.OrderDetail;
import com.saga.order.enums.OrderStatus;
import com.saga.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ConfirmCreateOrderAdapterProducer confirmCreateOrderAdapterProducer;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Order findById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Order create(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        String orderId = createOrderRequest.orderId() != null ? createOrderRequest.orderId() : UUID
                .randomUUID().toString();
        order.setId(orderId);
        order.setCode("ABED");
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATE_NEW.name());
        order.setUserIdOrder(createOrderRequest.userId());
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<CreateOrderRequest.CreateOrderDetailRequest> createOrderDetailRequests = createOrderRequest.orderRequests();
        for (CreateOrderRequest.CreateOrderDetailRequest orderDetailRequest : createOrderDetailRequests) {
            OrderDetail orderDetail = convert(orderDetailRequest.quantity(), orderDetailRequest.productId(), order);
            BigDecimal amount = orderDetailRequest.price().multiply(BigDecimal.valueOf(orderDetailRequest.quantity()));
            order.setTotalAmount(order.getTotalAmount().add(amount));
            orderDetails.add(orderDetail);
        }
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        confirmCreateOrderAdapterProducer.confirmCreateOrder(createOrderRequest);
        return order;
    }

    public Order update(UpdateOrderRequest updateOrderRequest) {
        Order order = orderRepository.findById(updateOrderRequest.orderId()).orElseThrow(() -> new NullPointerException("order not found"));
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (UpdateOrderRequest.UpdateOrderDetailRequest updateOrderDetailRequest : updateOrderRequest.updateOrderDetailRequests()) {
            OrderDetail orderDetail = convert(updateOrderDetailRequest.quantity(), updateOrderDetailRequest.productId(), order);
            BigDecimal amount = updateOrderDetailRequest.price().multiply(BigDecimal.valueOf(updateOrderDetailRequest.quantity()));
            order.setTotalAmount(order.getTotalAmount().add(amount));
            orderDetails.add(orderDetail);
        }
        order.getOrderDetails().clear();
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        return order;
    }

    public String updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NullPointerException("order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return orderId;
    }

    private OrderDetail convert(int quantity, Long productId, Order order) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setQuantity(quantity);
        orderDetail.setProductId(productId);
        return orderDetail;
    }
}
