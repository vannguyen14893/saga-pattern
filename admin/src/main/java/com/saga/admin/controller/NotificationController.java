package com.saga.admin.controller;

import com.saga.admin.dto.request.CreateNotificationRequest;
import com.saga.admin.dto.request.UpdateNotificationRequest;
import com.saga.admin.entity.Notification;
import com.saga.admin.service.NotificationService;
import com.saga.response.controller.BaseController;
import com.saga.response.dto.ResponseSuccess;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController extends BaseController {
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<Notification>>> findAll() {
        return execute(notificationService.findAll(), 200);
    }

    @GetMapping("/{type}")
    public ResponseEntity<ResponseSuccess<List<Notification>>> findAllType(@PathVariable String type) {
        return execute(notificationService.findAllByType(type), 200);
    }

    @GetMapping("/{type}/{language}")
    public ResponseEntity<ResponseSuccess<List<Notification>>> findAllTypeAndLanguage(@PathVariable String type, @PathVariable String language) {
        return execute(notificationService.findAllByTypeAndLanguage(type, language), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Notification>> findById(@PathVariable Long id) {
        return execute(notificationService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<Notification>> create(@RequestBody @Valid CreateNotificationRequest createNotificationRequest) {
        return execute(notificationService.create(createNotificationRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<Notification>> update(@RequestBody @Valid UpdateNotificationRequest updateNotificationRequest) {
        return execute(notificationService.update(updateNotificationRequest), 200);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> deleteById(@PathVariable Long id) {
        return execute(notificationService.delete(id), 200);
    }
}
