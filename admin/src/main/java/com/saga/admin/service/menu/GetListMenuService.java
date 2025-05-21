package com.saga.admin.service.menu;

import com.saga.admin.entity.Menu;
import com.saga.admin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving menu lists
 */
@Service
@RequiredArgsConstructor
public class GetListMenuService {
    private final MenuRepository menuRepository;

    /**
     * Retrieves all menu items
     *
     * @return List of all Menu entities
     */
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

}