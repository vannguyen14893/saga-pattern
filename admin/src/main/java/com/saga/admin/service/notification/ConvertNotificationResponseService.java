package com.saga.admin.service.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for converting Notification entities to NotificationResponse DTOs
 */
@Service
@RequiredArgsConstructor
public class ConvertNotificationResponseService {
    /**
     * Converts a Notification entity to NotificationResponse DTO
     *
     * @param notification The notification entity to convert
     * @return NotificationResponse DTO containing the notification data
     */
    public NotificationResponse convertToNotificationResponse(Notification notification) {
        return new NotificationResponse(notification.getId(), notification.getNotificationCode(),
                notification.getDescription(), notification.getEffectiveTime(),
                notification.getTitle(), notification.getTitle(), notification.getLanguageCode(), notification.getContent());
    }
}