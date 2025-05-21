package com.saga.admin.controller.menu;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.service.menu.FindMenuByUserIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling menu retrieval operations by user ID.
 * Provides endpoints for finding menus associated with specific users in the system.
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class FindMenuByUserIdController extends BaseController {
    private final FindMenuByUserIdService findMenuByUserIdService;


    /**
     * Retrieves all menus associated with the current user ID.
     *
     * @return ResponseEntity containing the list of menus with status 200
     */
    @GetMapping("/userId")
    public ResponseEntity<ResponseSuccess<List<MenuResponse>>> findByUserId() {
        return execute(findMenuByUserIdService.findAllMenuByUserId(), "200");
    }
}
