package com.saga.dto.enums;

import lombok.Getter;

public enum SmsType {
    OTHER(0),
    OTP(1),
    BALANCE_CHANGE(2);
    @Getter
    public final int value;

    private SmsType(int value) {
        this.value = value;
    }
}
