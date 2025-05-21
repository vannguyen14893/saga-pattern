package com.saga.admin.controller.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.GetListNotificationService;
import com.saga.dto.response.PaginationResult;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling notification listing operations.
 * Provides endpoints for retrieving all notifications in the system with pagination support.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class GetListNotificationController extends BaseController {
    private final GetListNotificationService getListNotificationService;

    /**
     * Retrieves all notifications with pagination support.
     *
     * @return ResponseEntity containing paginated list of notifications
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<NotificationResponse>>> findAll() {
        return execute(getListNotificationService.findAll(), getListNotificationService.findAll().size());
    }
}
