package com.saga.admin.service.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.entity.Languages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for converting Languages entity to LanguagesResponse DTO
 */
@Service
@RequiredArgsConstructor
public class ConvertLanguagesResponseService {

    /**
     * Converts Languages entity to LanguagesResponse DTO
     *
     * @param languages Languages entity to convert
     * @return LanguagesResponse DTO containing the language details
     */
    public LanguagesResponse convertToLanguages(Languages languages) {
        return new LanguagesResponse(languages.getId(),
                languages.getLanguageCode(),
                languages.getLanguageName(),
                languages.getLanguageDescription(),
                languages.getLanguageFlag(),
                languages.isActive());
    }
}