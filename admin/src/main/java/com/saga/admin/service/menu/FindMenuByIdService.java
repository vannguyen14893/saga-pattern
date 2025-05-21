package com.saga.admin.service.menu;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Menu;
import com.saga.admin.repository.MenuRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding menu items by ID
 */
@Service
@RequiredArgsConstructor
public class FindMenuByIdService {
    private final MenuRepository menuRepository;
    private final ConvertMenuResponseService convertMenuResponseService;

    /**
     * Finds a menu item by its ID
     *
     * @param id the ID of the menu item to find
     * @return MenuResponse containing the menu details
     * @throws NotFoundExceptionHandler if no menu is found with the given ID
     */
    public MenuResponse findById(Long id) {
        return convertMenuResponseService.convertToMenuResponse(menuRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Menu")));
    }
}