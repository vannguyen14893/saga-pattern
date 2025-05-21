package com.saga.admin.service.menu;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Menu;
import com.saga.admin.entity.Translate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class for converting Menu entities to MenuResponse DTOs
 */
@Service
@RequiredArgsConstructor
public class ConvertMenuResponseService {

    /**
     * Converts a Menu entity to a MenuResponse DTO
     *
     * @param menu the Menu entity to convert
     * @return MenuResponse containing the menu details and translations
     */
    public MenuResponse convertToMenuResponse(Menu menu) {
        Map<String, String> translates = menu.getTranslates().stream().collect(Collectors.toMap(
                Translate::getLanguageCode,
                Translate::getTranslation
        ));
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPath(), menu.getIcon(), menu.getOrders(), menu.getParentId(), translates);
    }
}