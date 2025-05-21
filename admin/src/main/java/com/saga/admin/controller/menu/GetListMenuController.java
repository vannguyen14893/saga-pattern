package com.saga.admin.controller.menu;

import com.saga.admin.entity.Menu;
import com.saga.admin.service.menu.GetListMenuService;
import com.saga.dto.response.PaginationResult;
import com.saga.response.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for handling menu listing operations.
 * Provides endpoints for retrieving all menus in the system with pagination support.
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class GetListMenuController extends BaseController {
    private final GetListMenuService getListMenuService;

    /**
     * Retrieves all menus with pagination support.
     *
     * @return ResponseEntity containing paginated list of menus
     */
    @GetMapping
    public ResponseEntity<PaginationResult<List<Menu>>> findAll() {
        return execute(getListMenuService.findAll(), getListMenuService.findAll().size());
    }

}
