package com.saga.admin.service.languages;

import com.saga.admin.dto.request.languages.UpdateLanguagesRequest;
import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.entity.Languages;
import com.saga.admin.repository.LanguagesRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for updating language entries
 */
@Service
@RequiredArgsConstructor
public class UpdateLanguagesService {
    private final LanguagesRepository languagesRepository;
    private final ConvertLanguagesResponseService convertLanguagesResponseService;

    /**
     * Updates an existing language entry
     *
     * @param request The UpdateLanguagesRequest containing updated language details
     * @return LanguagesResponse containing the updated language details
     * @throws NotFoundExceptionHandler if language with given ID is not found
     */
    public LanguagesResponse update(UpdateLanguagesRequest request) {
        Languages language = languagesRepository.findById(request.id()).orElseThrow(() -> new NotFoundExceptionHandler("Language"));
        language.setLanguageCode(request.languageCode());
        language.setLanguageName(request.languageName());
        language.setLanguageFlag(request.languageFlag());
        language.setLanguageDescription(request.languageDescription());
        language.setActive(request.active());
        return convertLanguagesResponseService.convertToLanguages(languagesRepository.save(language));
    }

}