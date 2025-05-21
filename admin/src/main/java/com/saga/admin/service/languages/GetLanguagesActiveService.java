package com.saga.admin.service.languages;

import com.saga.admin.dto.response.languages.LanguagesResponse;
import com.saga.admin.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving active language entries
 */
@Service
@RequiredArgsConstructor
public class GetLanguagesActiveService {
    private final LanguagesRepository languagesRepository;
    private final ConvertLanguagesResponseService convertLanguagesResponseService;

    /**
     * Retrieves all active language entries
     *
     * @return List of LanguagesResponse containing active language details
     */
    public List<LanguagesResponse> findAllByActiveIsTrue() {
        return languagesRepository.findAllByActiveIsTrue().stream().map(convertLanguagesResponseService::convertToLanguages).toList();
    }

}