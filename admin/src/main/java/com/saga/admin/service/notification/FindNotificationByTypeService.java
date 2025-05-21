package com.saga.admin.service.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for finding notifications by type
 */
@Service
@RequiredArgsConstructor
public class FindNotificationByTypeService {
    private final NotificationRepository notificationRepository;
    private final ConvertNotificationResponseService convertNotificationResponseService;

    /**
     * Finds all notifications of a specific type
     *
     * @param type The notification type to search for
     * @return List of NotificationResponse matching the type
     */
    public List<NotificationResponse> findAllByType(String type) {
        return notificationRepository.findAllByType(type).map(convertNotificationResponseService::convertToNotificationResponse).collect(Collectors.toList());
    }

}