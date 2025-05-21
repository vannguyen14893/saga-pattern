package com.saga.admin.service.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.repository.LanguagesRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for finding language entries by ID
 */
@Service
@RequiredArgsConstructor
public class FindLanguagesByIdService {
    private final LanguagesRepository languagesRepository;
    private final ConvertLanguagesResponseService convertLanguagesResponseService;

    /**
     * Finds a language entry by its ID
     *
     * @param id The ID of the language to find
     * @return LanguagesResponse containing the found language details
     * @throws NotFoundExceptionHandler if language with given ID is not found
     */
    public LanguagesResponse findById(Long id) {
        return convertLanguagesResponseService.convertToLanguages(languagesRepository.findById(id).orElseThrow(() -> new NotFoundExceptionHandler("Language")));
    }

}