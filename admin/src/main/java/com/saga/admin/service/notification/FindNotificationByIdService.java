package com.saga.admin.service.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.repository.NotificationRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for finding notifications by ID
 */
@Service
@RequiredArgsConstructor
public class FindNotificationByIdService {
    private final NotificationRepository notificationRepository;
    private final ConvertNotificationResponseService convertNotificationResponseService;

    /**
     * Finds a notification by its ID
     *
     * @param id The ID of the notification to find
     * @return NotificationResponse containing the found notification data
     * @throws NotFoundExceptionHandler if notification is not found
     */
    public NotificationResponse findById(Long id) {
        return convertNotificationResponseService.convertToNotificationResponse(notificationRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Notification")));
    }

}