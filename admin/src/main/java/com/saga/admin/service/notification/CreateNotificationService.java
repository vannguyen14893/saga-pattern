package com.saga.admin.service.notification;

import com.saga.admin.dto.request.notification.CreateNotificationRequest;
import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.entity.Notification;
import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNotificationService {
    private final NotificationRepository notificationRepository;
    private final ConvertNotificationResponseService convertNotificationResponseService;

    public NotificationResponse create(CreateNotificationRequest createNotificationRequest) {
        Notification notification = new Notification();
        notification.setContent(createNotificationRequest.content());
        notification.setDescription(createNotificationRequest.description());
        notification.setTitle(createNotificationRequest.title());
        notification.setLanguageCode(createNotificationRequest.languageCode());
        notification.setType(createNotificationRequest.type());
        notification.setEffectiveTime(createNotificationRequest.effectiveTime());
        return convertNotificationResponseService.convertToNotificationResponse(notificationRepository.save(notification));
    }
}
