package com.saga.admin.service.menu;

import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Menu;
import com.saga.admin.entity.Translate;
import com.saga.admin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindMenuByUserIdService {
    private final MenuRepository menuRepository;



    public List<MenuResponse> findAllMenuByUserId() {
        return menuRepository.findAll().stream().map(this::convertToMenuResponse).collect(Collectors.toList());
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        Map<String, String> translates = menu.getTranslates().stream().collect(Collectors.toMap(
                Translate::getLanguageCode,
                Translate::getTranslation
        ));
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPath(), menu.getIcon(), menu.getOrders(), menu.getParentId(), translates);
    }
}
