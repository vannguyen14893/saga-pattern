package com.saga.admin.controller.menu;

import com.saga.admin.dto.request.menu.CreateMenuRequest;
import com.saga.admin.dto.request.menu.UpdateMenuRequest;
import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.service.menu.CreateMenuService;
import com.saga.admin.service.menu.UpdateMenuService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling menu update operations.
 * Provides endpoints for modifying existing menus in the system.
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class UpdateMenuController extends BaseController {
    private final UpdateMenuService updateMenuService;


    /**
     * Updates an existing menu with the provided data.
     *
     * @param updateMenuRequest The request containing updated menu data
     * @return ResponseEntity containing the updated menu with status 200
     */
    @PutMapping()
    public ResponseEntity<ResponseSuccess<MenuResponse>> create(@RequestBody @Valid UpdateMenuRequest updateMenuRequest) {
        return execute(updateMenuService.update(updateMenuRequest), "200");
    }
}
