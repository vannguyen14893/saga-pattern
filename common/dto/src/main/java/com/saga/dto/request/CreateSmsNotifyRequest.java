package com.saga.dto.request;

import com.saga.dto.enums.SmsType;

public record CreateSmsNotifyRequest(String phone, String message, SmsType smsType) {
}
