package com.saga.admin.service.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving all language entries
 */
@Service
@RequiredArgsConstructor
public class GetListLanguagesService {
    private final LanguagesRepository languagesRepository;
    private final ConvertLanguagesResponseService convertLanguagesResponseService;

    /**
     * Retrieves all language entries
     *
     * @return List of LanguagesResponse containing all language details
     */
    public List<LanguagesResponse> findAll() {
        return languagesRepository.findAll().stream().map(convertLanguagesResponseService::convertToLanguages).toList();
    }
}