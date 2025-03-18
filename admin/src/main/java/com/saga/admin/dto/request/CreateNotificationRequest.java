package com.saga.admin.dto.request;

import java.util.Date;


public record CreateNotificationRequest(String notificationCode, String description, Date effectiveTime,
                                        String title, String type, String languageCode, String content) {
}

