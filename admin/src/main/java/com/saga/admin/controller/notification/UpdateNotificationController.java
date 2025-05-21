package com.saga.admin.controller.notification;

import com.saga.admin.dto.request.notification.UpdateNotificationRequest;
import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.UpdateNotificationService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling notification update operations.
 * Provides endpoints for modifying existing notifications in the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class UpdateNotificationController extends BaseController {
    private final UpdateNotificationService updateNotificationService;

    /**
     * Updates an existing notification with the provided data.
     *
     * @param updateNotificationRequest The request containing updated notification data
     * @return ResponseEntity containing the updated notification with status 200
     */
    @PutMapping
    public ResponseEntity<ResponseSuccess<NotificationResponse>> update(@RequestBody @Valid UpdateNotificationRequest updateNotificationRequest) {
        return execute(updateNotificationService.update(updateNotificationRequest), "200");
    }

}
