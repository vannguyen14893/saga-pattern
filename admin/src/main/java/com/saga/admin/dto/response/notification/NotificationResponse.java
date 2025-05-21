package com.saga.admin.dto.response.notification;

import java.util.Date;

public record NotificationResponse(Long id,
                                   String notificationCode,
                                   String description,
                                   Date effectiveTime,
                                   String title,
                                   String type,
                                   String languageCode,
                                   String content) {
}
