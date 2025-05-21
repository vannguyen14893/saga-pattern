package com.saga.admin.service.menu;

import com.saga.admin.dto.request.menu.CreateMenuRequest;
import com.saga.admin.dto.request.translate.TranslateRequest;
import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.entity.Menu;
import com.saga.admin.entity.Translate;
import com.saga.admin.enums.GroupEnums;
import com.saga.admin.repository.MenuRepository;
import com.saga.admin.service.group.FindGroupByTypeAndIdInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Service class for creating menu items
 */
@Service
@RequiredArgsConstructor
public class CreateMenuService {
    private final MenuRepository menuRepository;
    private final FindGroupByTypeAndIdInService findGroupByTypeAndIdInService;
    private final ConvertMenuResponseService convertMenuResponseService;

    /**
     * Creates a new menu item based on the provided request
     *
     * @param createMenuRequest the request containing menu item details
     * @return MenuResponse object containing the created menu details
     */
    public MenuResponse create(CreateMenuRequest createMenuRequest) {
        Menu menu = new Menu();
        menu.setName(createMenuRequest.key());
        menu.setIcon(createMenuRequest.icon());
        menu.setPath(createMenuRequest.path());
        menu.setOrders(createMenuRequest.order());
        menu.setParentId(createMenuRequest.parentId());
        Set<Group> groups = findGroupByTypeAndIdInService.findAllByTypeAndIdIn(createMenuRequest.groupIds(), GroupEnums.GROUP_MENU.name());
        menu.setGroups(groups);
        List<Translate> translates = createMenuRequest.translates().stream().map(this::convertToTranslate).toList();
        menu.setTranslates(translates);
        return convertMenuResponseService.convertToMenuResponse(menuRepository.save(menu));
    }

    /**
     * Converts a TranslateRequest to Translate entity
     *
     * @param translateRequest the request containing translation details
     * @return Translate entity with the translation details
     */
    private Translate convertToTranslate(TranslateRequest translateRequest) {
        Translate translate = new Translate();
        translate.setLanguageCode(translateRequest.languageCode());
        translate.setTranslation(translateRequest.translation());
        return translate;
    }
}