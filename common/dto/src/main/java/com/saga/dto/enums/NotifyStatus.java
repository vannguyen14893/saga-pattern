package com.saga.dto.enums;

import lombok.Getter;

public enum NotifyStatus {
    PENDING(1),
    SUCCESS(2),
    FALSE(0);
    @Getter
    private final int value;

    private NotifyStatus(int value) {
        this.value = value;
    }
}
