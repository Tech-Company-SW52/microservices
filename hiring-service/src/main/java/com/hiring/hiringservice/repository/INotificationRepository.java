package com.hiring.hiringservice.repository;

import com.hiring.hiringservice.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificationRepository extends JpaRepository<Notification, Long> {
    Notification findByReadStatus(boolean status);
}
