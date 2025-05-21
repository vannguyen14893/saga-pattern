package com.saga.admin.service.languages;

import com.saga.admin.repository.LanguagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for deleting language entries
 */
@Service
@RequiredArgsConstructor
public class DeleteLanguagesService {
    private final LanguagesRepository languagesRepository;

    /**
     * Deletes a language entry by ID
     *
     * @param id The ID of the language to delete
     * @return ID of the deleted language
     */
    public Long delete(Long id) {
        languagesRepository.deleteById(id);
        return id;
    }
}