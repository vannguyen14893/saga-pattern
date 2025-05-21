package com.saga.admin.service.menu;

import com.saga.admin.dto.request.menu.UpdateMenuRequest;
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
 * Service class for updating menu items
 */
@Service
@RequiredArgsConstructor
public class UpdateMenuService {
    private final MenuRepository menuRepository;
    private final FindGroupByTypeAndIdInService findGroupByTypeAndIdInService;
    private final ConvertMenuResponseService convertMenuResponseService;
    private final FindMenuByIdService findMenuByIdService;

    /**
     * Updates an existing menu item based on the provided request
     *
     * @param updateMenuRequest the request containing updated menu item details
     * @return MenuResponse containing the updated menu details
     */
    public MenuResponse update(UpdateMenuRequest updateMenuRequest) {
        MenuResponse menuResponse = findMenuByIdService.findById(updateMenuRequest.id());
        Menu menu = new Menu();
        menu.setId(menuResponse.id());
        menu.setName(updateMenuRequest.key());
        menu.setIcon(updateMenuRequest.icon());
        menu.setPath(updateMenuRequest.path());
        menu.setOrders(updateMenuRequest.order());
        menu.setParentId(updateMenuRequest.parentId());
        menu.getGroups().clear();
        Set<Group> groups = findGroupByTypeAndIdInService.findAllByTypeAndIdIn(updateMenuRequest.groupIds(), GroupEnums.GROUP_MENU.name());
        menu.setGroups(groups);
        menu.getTranslates().clear();
        List<Translate> translates = updateMenuRequest.translates().stream().map(this::convertToTranslate).toList();
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