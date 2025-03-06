package com.saga.delivery.adapter.dto;

import java.time.LocalDate;

public record CreateDeliveryAdapterRequest(LocalDate expectedDate, String address, Long orderId) {
}
