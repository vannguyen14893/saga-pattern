package com.saga.admin.service.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for retrieving all notifications from the system
 */
@Service
@RequiredArgsConstructor
public class GetListNotificationService {
    private final NotificationRepository notificationRepository;
    private final ConvertNotificationResponseService convertNotificationResponseService;

    /**
     * Retrieves all notifications
     *
     * @return List of all NotificationResponse objects in the system
     */
    public List<NotificationResponse> findAll() {
        return notificationRepository.findAll().stream().map(convertNotificationResponseService::convertToNotificationResponse).toList();
    }

}