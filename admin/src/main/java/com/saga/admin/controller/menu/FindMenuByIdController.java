package com.saga.admin.controller.menu;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Menu;
import com.saga.admin.service.menu.FindMenuByIdService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling menu retrieval operations by ID.
 * Provides endpoints for finding specific menus in the system using their unique identifiers.
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class FindMenuByIdController extends BaseController {
    private final FindMenuByIdService findMenuByIdService;


    /**
     * Retrieves a menu by its unique identifier.
     *
     * @param id The unique identifier of the menu to retrieve
     * @return ResponseEntity containing the found menu with status 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<MenuResponse>> findById(@PathVariable Long id) {
        return execute(findMenuByIdService.findById(id), "200");
    }
}
