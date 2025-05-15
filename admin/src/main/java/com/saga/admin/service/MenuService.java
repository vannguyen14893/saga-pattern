package com.saga.admin.service;

import com.saga.admin.dto.request.menu.CreateMenuRequest;
import com.saga.admin.dto.request.menu.UpdateMenuRequest;
import com.saga.admin.dto.request.translate.TranslateRequest;
import com.saga.admin.dto.response.menu.MenuResponse;
import com.saga.admin.entity.Group;
import com.saga.admin.entity.Menu;
import com.saga.admin.entity.Translate;
import com.saga.admin.repository.MenuRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final GroupService groupService;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public List<MenuResponse> findAllMenuByUserId() {
        return menuRepository.findAll().stream().map(this::convertToMenuResponse).collect(Collectors.toList());
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Menu"));
    }

    public MenuResponse create(CreateMenuRequest createMenuRequest) {
        Menu menu = new Menu();
        menu.setName(createMenuRequest.key());
        menu.setIcon(createMenuRequest.icon());
        menu.setPath(createMenuRequest.path());
        menu.setOrders(createMenuRequest.order());
        menu.setParentId(createMenuRequest.parentId());
        Set<Group> groups = groupService.findAllByTypeAndIdIn(createMenuRequest.groupIds(), "GROUP_MENU");
        menu.setGroups(groups);
        List<Translate> translates = createMenuRequest.translates().stream().map(item -> convertToTranslate(item, menu)).toList();
        menu.setTranslates(translates);
        return convertToMenuResponse(menuRepository.save(menu));
    }

    public MenuResponse update(UpdateMenuRequest updateMenuRequest) {
        Menu menu = menuRepository.findById(updateMenuRequest.id()).orElseThrow(() -> new NotFoundExceptionHandler("Menu"));
        menu.setName(updateMenuRequest.key());
        menu.setIcon(updateMenuRequest.icon());
        menu.setPath(updateMenuRequest.path());
        menu.setOrders(updateMenuRequest.order());
        menu.setParentId(updateMenuRequest.parentId());
        menu.getGroups().clear();
        Set<Group> groups = groupService.findAllByTypeAndIdIn(updateMenuRequest.groupIds(), "GROUP_MENU");
        menu.setGroups(groups);
        menu.getTranslates().clear();
        List<Translate> translates = updateMenuRequest.translates().stream().map(item -> convertToTranslate(item, menu)).toList();
        menu.setTranslates(translates);
        return convertToMenuResponse(menuRepository.save(menu));
    }

    private Translate convertToTranslate(TranslateRequest translateRequest, Menu menu) {
        Translate translate = new Translate();
        translate.setLanguageCode(translateRequest.languageCode());
        translate.setTranslation(translateRequest.translation());
        translate.setMenu(menu);
        return translate;
    }

    private MenuResponse convertToMenuResponse(Menu menu) {
        Map<String, String> translates = menu.getTranslates().stream().collect(Collectors.toMap(
                Translate::getLanguageCode,
                Translate::getTranslation
        ));
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPath(), menu.getIcon(), menu.getOrders(), menu.getParentId(), translates);
    }
}
