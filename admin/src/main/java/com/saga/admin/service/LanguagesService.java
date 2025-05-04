package com.saga.admin.service;

import com.saga.admin.dto.request.languages.CreateLanguagesRequest;
import com.saga.admin.dto.request.languages.UpdateLanguagesRequest;
import com.saga.admin.entity.Languages;
import com.saga.admin.repository.LanguagesRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguagesService {
    private final LanguagesRepository languagesRepository;

    public List<Languages> findAll() {
        return languagesRepository.findAll();
    }

    public List<Languages> findAllByActiveIsTrue() {
        return languagesRepository.findAllByActiveIsTrue();
    }

    public Languages findById(Long id) {
        return languagesRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Language"));
    }

    public Languages create(CreateLanguagesRequest request) {
        Languages languages = new Languages();
        languages.setLanguageCode(request.languageCode());
        languages.setLanguageName(request.languageName());
        languages.setLanguageFlag(request.languageFlag());
        languages.setLanguageDescription(request.languageDescription());
        languages.setActive(request.active());
        return languagesRepository.save(languages);
    }

    public Languages update(UpdateLanguagesRequest request) {
        Languages language = languagesRepository.findById(request.id()).orElseThrow(() -> new NotFoundExceptionHandler("Language"));
        language.setLanguageCode(request.languageCode());
        language.setLanguageName(request.languageName());
        language.setLanguageFlag(request.languageFlag());
        language.setLanguageDescription(request.languageDescription());
        language.setActive(request.active());
        return languagesRepository.save(language);
    }

    public Long delete(Long id) {
        languagesRepository.deleteById(id);
        return id;
    }
}
