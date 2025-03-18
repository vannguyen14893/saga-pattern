package com.saga.dto.request;

import java.math.BigDecimal;

public record TransactionAdapterRequest(String code, String type, BigDecimal amount, String status,
                                        String transactionDate, Long userId) {
}
