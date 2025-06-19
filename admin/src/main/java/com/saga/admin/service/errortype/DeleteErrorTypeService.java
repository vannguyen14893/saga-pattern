package com.saga.admin.service.errortype;

import com.saga.admin.repository.ErrorTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for deleting error types
 */
@Service
@RequiredArgsConstructor
public class DeleteErrorTypeService {
    private final ErrorTypeRepository errorTypeRepository;

    /**
     * Deletes an error type by its ID
     *
     * @param id The ID of the error type to delete
     * @return The ID of the deleted error type
     */
    public Long delete(Long id) {
        errorTypeRepository.deleteById(id);
        return id;
    }
}

