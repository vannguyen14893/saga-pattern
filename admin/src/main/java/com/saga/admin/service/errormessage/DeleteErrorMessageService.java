package com.saga.admin.service.errormessage;

import com.saga.admin.repository.ErrorMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for deleting error messages
 */
@Service
@RequiredArgsConstructor
public class DeleteErrorMessageService {
    private final ErrorMessageRepository errorMessageRepository;

    /**
     * Deletes an error message by its ID
     *
     * @param id The ID of the error message to delete
     * @return The ID of the deleted error message
     */
    public Long delete(Long id) {
        errorMessageRepository.deleteById(id);
        return id;
    }
}

