package com.saga.delivery.service;

import com.saga.delivery.dto.request.CreateDeliveryRequest;
import com.saga.delivery.entity.Delivery;
import com.saga.delivery.enums.DeliveryStatus;
import com.saga.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public List<Delivery> list() {
        return deliveryRepository.findAll();
    }

    public Delivery findById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    public Delivery findByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    public Delivery create(CreateDeliveryRequest createDeliveryRequest) {
        Delivery delivery = new Delivery();
        delivery.setStatus(DeliveryStatus.CREATE_NEW.name());
        delivery.setAddress(createDeliveryRequest.address());
        delivery.setActualDate(LocalDate.now().plusDays(1));
        delivery.setExpectedDate(createDeliveryRequest.expectedDate());
        delivery.setOrderId(createDeliveryRequest.orderId());
        delivery.setCreatedDate(LocalDateTime.now());
        return deliveryRepository.save(delivery);
    }

    public Long updateStatus(Long orderId, String status) {
        Delivery delivery = deliveryRepository.findByOrderId(orderId);
        delivery.setStatus(status);
        deliveryRepository.save(delivery);
        return orderId;
    }
}
