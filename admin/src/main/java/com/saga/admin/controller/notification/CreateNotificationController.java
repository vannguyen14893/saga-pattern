package com.saga.admin.controller.notification;

import com.saga.admin.dto.request.notification.CreateNotificationRequest;
import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.CreateNotificationService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling notification creation operations.
 * Provides endpoints for creating new notifications in the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class CreateNotificationController extends BaseController {
    private final CreateNotificationService createNotificationService;

    /**
     * Creates a new notification based on the provided request.
     *
     * @param createNotificationRequest The request containing notification details
     * @return ResponseEntity containing the created notification response with status 201
     */
    @PostMapping
    public ResponseEntity<ResponseSuccess<NotificationResponse>> create(@RequestBody @Valid CreateNotificationRequest createNotificationRequest) {
        return execute(createNotificationService.create(createNotificationRequest), "201");
    }
}
