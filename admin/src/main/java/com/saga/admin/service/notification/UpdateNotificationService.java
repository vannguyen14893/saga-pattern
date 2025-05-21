package com.saga.admin.service.notification;

import com.saga.admin.dto.request.notification.UpdateNotificationRequest;
import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.entity.Notification;
import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for updating existing notifications in the system
 */
@Service
@RequiredArgsConstructor
public class UpdateNotificationService {
    private final FindNotificationByIdService findNotificationByIdService;
    private final ConvertNotificationResponseService convertNotificationResponseService;
    private final NotificationRepository notificationRepository;

    /**
     * Updates an existing notification with new data
     *
     * @param updateNotificationRequest The request containing updated notification data
     * @return NotificationResponse containing the updated notification data
     */
    public NotificationResponse update(UpdateNotificationRequest updateNotificationRequest) {
        NotificationResponse notificationResponse = findNotificationByIdService.findById(updateNotificationRequest.id());
        Notification notification = new Notification();
        notification.setId(notificationResponse.id());
        notification.setDescription(updateNotificationRequest.description());
        notification.setTitle(updateNotificationRequest.title());
        notification.setLanguageCode(updateNotificationRequest.languageCode());
        notification.setType(updateNotificationRequest.type());
        notification.setContent(updateNotificationRequest.content());
        notification.setEffectiveTime(updateNotificationRequest.effectiveTime());
        return convertNotificationResponseService.convertToNotificationResponse(notificationRepository.save(notification));
    }
}