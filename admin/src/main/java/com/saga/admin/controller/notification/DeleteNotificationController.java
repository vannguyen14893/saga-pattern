package com.saga.admin.controller.notification;

import com.saga.admin.service.notification.DeleteNotificationService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling notification deletion operations.
 * Provides endpoints for removing notifications from the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class DeleteNotificationController extends BaseController {
    private final DeleteNotificationService deleteNotificationService;


    /**
     * Deletes a notification by its ID.
     *
     * @param id The ID of the notification to delete
     * @return ResponseEntity containing the deleted notification ID with status 200
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> deleteById(@PathVariable Long id) {
        return execute(deleteNotificationService.delete(id), "200");
    }
}
