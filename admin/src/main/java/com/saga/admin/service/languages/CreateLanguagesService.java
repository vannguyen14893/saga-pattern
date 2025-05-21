package com.saga.admin.service.languages;

import com.saga.admin.dto.request.languages.CreateLanguagesRequest;
import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.entity.Languages;
import com.saga.admin.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for creating new language entries
 */
@Service
@RequiredArgsConstructor
public class CreateLanguagesService {
    private final LanguagesRepository languagesRepository;
    private final ConvertLanguagesResponseService convertLanguagesResponseService;

    /**
     * Creates a new language entry
     *
     * @param request The CreateLanguagesRequest containing language details
     * @return LanguagesResponse object containing the created language details
     */
    public LanguagesResponse create(CreateLanguagesRequest request) {
        Languages languages = new Languages();
        languages.setLanguageCode(request.languageCode());
        languages.setLanguageName(request.languageName());
        languages.setLanguageFlag(request.languageFlag());
        languages.setLanguageDescription(request.languageDescription());
        languages.setActive(request.active());
        return convertLanguagesResponseService.convertToLanguages(languagesRepository.save(languages));
    }
}