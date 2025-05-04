package com.saga.admin.service;

import com.saga.admin.dto.request.notification.CreateNotificationRequest;
import com.saga.admin.dto.request.notification.UpdateNotificationRequest;
import com.saga.admin.entity.Notification;
import com.saga.admin.repository.NotificationRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public List<Notification> findAllByType(String type) {
        return notificationRepository.findAllByType(type).collect(Collectors.toList());
    }

    public List<Notification> findAllByTypeAndLanguage(String type, String language) {
        return notificationRepository.findAllByTypeAndLanguageCode(type, language).collect(Collectors.toList());
    }

    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("notification"));
    }

    public Notification create(CreateNotificationRequest createNotificationRequest) {
        Notification notification = new Notification();
        notification.setContent(createNotificationRequest.content());
        notification.setDescription(createNotificationRequest.description());
        notification.setTitle(createNotificationRequest.title());
        notification.setLanguageCode(createNotificationRequest.languageCode());
        notification.setType(createNotificationRequest.type());
        notification.setEffectiveTime(createNotificationRequest.effectiveTime());
        return notificationRepository.save(notification);
    }

    public Notification update(UpdateNotificationRequest updateNotificationRequest) {
        Notification notification = notificationRepository.findById(updateNotificationRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("notification"));
        notification.setDescription(updateNotificationRequest.description());
        notification.setTitle(updateNotificationRequest.title());
        notification.setLanguageCode(updateNotificationRequest.languageCode());
        notification.setType(updateNotificationRequest.type());
        notification.setContent(updateNotificationRequest.content());
        notification.setEffectiveTime(updateNotificationRequest.effectiveTime());
        return notificationRepository.save(notification);
    }

    public Long delete(Long id) {
        notificationRepository.deleteById(id);
        return id;
    }
}
