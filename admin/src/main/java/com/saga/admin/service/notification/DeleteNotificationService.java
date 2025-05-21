package com.saga.admin.service.notification;

import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for deleting notifications from the system
 */
@Service
@RequiredArgsConstructor
public class DeleteNotificationService {
    private final NotificationRepository notificationRepository;

    /**
     * Deletes a notification by its ID
     *
     * @param id The ID of the notification to delete
     * @return The ID of the deleted notification
     */
    public Long delete(Long id) {
        notificationRepository.deleteById(id);
        return id;
    }
}