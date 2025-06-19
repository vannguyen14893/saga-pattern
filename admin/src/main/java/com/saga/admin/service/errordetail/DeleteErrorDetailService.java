package com.saga.admin.service.errordetail;

import com.saga.admin.repository.ErrorDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for deleting error details
 */
@Service
@RequiredArgsConstructor
public class DeleteErrorDetailService {
    private final ErrorDetailRepository errorDetailRepository;

    /**
     * Deletes an error detail by its ID
     *
     * @param id The ID of the error detail to delete
     * @return The ID of the deleted error detail
     */
    public Long delete(Long id) {
        errorDetailRepository.deleteById(id);
        return id;
    }
}

