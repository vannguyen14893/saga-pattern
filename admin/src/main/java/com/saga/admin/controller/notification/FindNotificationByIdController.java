package com.saga.admin.controller.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.FindNotificationByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling notification retrieval operations.
 * Provides endpoints for finding notifications by their ID in the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class FindNotificationByIdController extends BaseController {
    private final FindNotificationByIdService findNotificationByIdService;


    /**
     * Retrieves a notification by its ID.
     *
     * @param id The ID of the notification to retrieve
     * @return ResponseEntity containing the found notification with status 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<NotificationResponse>> findById(@PathVariable Long id) {
        return execute(findNotificationByIdService.findById(id), "200");
    }

}
