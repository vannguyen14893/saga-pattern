package com.saga.admin.service.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for finding notifications by type and language
 */
@Service
@RequiredArgsConstructor
public class FindNotificationByTypeAndLanguageService {
    private final NotificationRepository notificationRepository;
    private final ConvertNotificationResponseService convertNotificationResponseService;

    /**
     * Finds notifications by type and language code
     *
     * @param type     The notification type to search for
     * @param language The language code to search for
     * @return List of NotificationResponse matching the type and language
     */
    public List<NotificationResponse> findAllByTypeAndLanguage(String type, String language) {
        return notificationRepository.findAllByTypeAndLanguageCode(type, language).map(convertNotificationResponseService::convertToNotificationResponse).collect(Collectors.toList());
    }
}