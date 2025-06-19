package com.saga.admin.service.errorsystem;

import com.saga.admin.repository.ErrorSystemRepository;
import com.saga.exceptions.exceptions.NotFoundExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to delete an ErrorSystem by id.
 */
@Service
@RequiredArgsConstructor
public class DeleteErrorSystemService {
    private final ErrorSystemRepository errorSystemRepository;

    @Transactional
    public Long deleteById(Long id) {
        if (!errorSystemRepository.existsById(id)) {
            throw new NotFoundExceptionHandler("ErrorSystem");
        }
        errorSystemRepository.deleteById(id);
        return id;
    }
}

