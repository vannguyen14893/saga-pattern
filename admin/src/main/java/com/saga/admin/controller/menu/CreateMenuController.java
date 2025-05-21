package com.saga.admin.controller.menu;

import com.saga.admin.dto.request.menu.CreateMenuRequest;
import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.service.menu.CreateMenuService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling menu creation operations.
 * Provides endpoints for creating new menus in the system.
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class CreateMenuController extends BaseController {
    private final CreateMenuService createMenuService;


    /**
     * Creates a new menu with the provided data.
     *
     * @param createMenuRequest The request containing menu data to create
     * @return ResponseEntity containing the created menu with status 200
     */
    @PostMapping()
    public ResponseEntity<ResponseSuccess<MenuResponse>> create(@RequestBody @Valid CreateMenuRequest createMenuRequest) {
        return execute(createMenuService.create(createMenuRequest), "200");
    }
}
