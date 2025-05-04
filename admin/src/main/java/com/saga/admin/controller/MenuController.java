package com.saga.admin.controller;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Menu;
import com.saga.admin.service.MenuService;
import com.saga.response.controller.BaseController;
import com.saga.response.dto.ResponseSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController {
    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<Menu>>> findAll() {
        return execute(menuService.findAll(), 200);
    }

    @GetMapping("/userId")
    public ResponseEntity<ResponseSuccess<List<MenuResponse>>> findAllMenuByUserId() {
        return execute(menuService.findAllMenuByUserId(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Menu>> findById(@PathVariable Long id) {
        return execute(menuService.findById(id), 200);
    }
}
