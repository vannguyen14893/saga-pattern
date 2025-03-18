package com.saga.admin.repository;

import com.saga.admin.entity.Notification;
import jakarta.persistence.QueryHint;
import org.hibernate.jpa.AvailableHints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.stream.Stream;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    Stream<Notification> findAllByTypeAndLanguageCode(String type, String language);

    @QueryHints(
            @QueryHint(name = AvailableHints.HINT_FETCH_SIZE, value = "25")
    )
    Stream<Notification> findAllByType(String type);
}
