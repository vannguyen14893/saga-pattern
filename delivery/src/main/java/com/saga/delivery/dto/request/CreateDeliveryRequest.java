package com.saga.delivery.dto.request;

import java.time.LocalDate;

public record CreateDeliveryRequest(LocalDate expectedDate, String address, Long orderId) {
}
