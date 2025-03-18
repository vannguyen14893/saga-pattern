package com.saga.dto.enums;

import lombok.Getter;

public enum EmailType {
    TO_ONE(1),
    TO_MANY(2),
    TO_ALL(3);
    @Getter
    private final int value;

    private EmailType(int value) {
        this.value = value;
    }

}
