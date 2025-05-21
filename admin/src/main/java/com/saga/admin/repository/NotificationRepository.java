package com.saga.admin.repository;

import com.saga.admin.entity.Notification;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.stream.Stream;

/**
 * Repository interface for managing Notification entities.
 * Provides CRUD operations and custom queries for Notification data access.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /**
     * Retrieves all notifications of a specific type and language code.
     *
     * @param type     the type of notifications to retrieve
     * @param language the language code to filter notifications
     * @return a Stream of Notification entities matching the criteria
     */
    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    Stream<Notification> findAllByTypeAndLanguageCode(String type, String language);

    /**
     * Retrieves all notifications of a specific type.
     *
     * @param type the type of notifications to retrieve
     * @return a Stream of Notification entities matching the type
     */
    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    Stream<Notification> findAllByType(String type);
}
