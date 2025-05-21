package com.saga.admin.controller.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.FindNotificationByTypeService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling notification retrieval operations by type.
 * Provides endpoints for finding notifications filtered by their type in the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class FindNotificationByTypeController extends BaseController {
    private final FindNotificationByTypeService findNotificationByTypeService;

    /**
     * Retrieves all notifications of a specific type.
     *
     * @param type The type of notifications to retrieve
     * @return ResponseEntity containing the list of notifications with status 200
     */
    @GetMapping("/{type}")
    public ResponseEntity<ResponseSuccess<List<NotificationResponse>>> findAllType(@PathVariable String type) {
        return execute(findNotificationByTypeService.findAllByType(type), "200");
    }

}
