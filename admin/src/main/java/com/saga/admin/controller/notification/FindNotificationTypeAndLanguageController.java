package com.saga.admin.controller.notification;

import com.saga.admin.dto.response.notification.NotificationResponse;
import com.saga.admin.service.notification.FindNotificationByTypeAndLanguageService;
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
 * REST controller for handling notification retrieval operations by type and language.
 * Provides endpoints for finding notifications filtered by their type and language in the system.
 */
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class FindNotificationTypeAndLanguageController extends BaseController {
    private final FindNotificationByTypeAndLanguageService findNotificationByTypeAndLanguageService;


    /**
     * Retrieves all notifications of a specific type and language.
     *
     * @param type     The type of notifications to retrieve
     * @param language The language of notifications to retrieve
     * @return ResponseEntity containing the list of notifications with status 200
     */
    @GetMapping("/{type}/{language}")
    public ResponseEntity<ResponseSuccess<List<NotificationResponse>>> findAllTypeAndLanguage(@PathVariable String type, @PathVariable String language) {
        return execute(findNotificationByTypeAndLanguageService.findAllByTypeAndLanguage(type, language), "200");
    }

}
