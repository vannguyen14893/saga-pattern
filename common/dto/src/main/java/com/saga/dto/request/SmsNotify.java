package com.saga.dto.request;

import com.saga.dto.enums.NotifyStatus;
import com.saga.dto.enums.SmsType;

public record SmsNotify(String phone, String message, SmsType smsType, NotifyStatus status) {
}
