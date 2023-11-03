package com.hiring.hiringservice.service;

import com.hiring.hiringservice.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface INotificationService {

    Notification save(Notification notification);

    void delete(Long id);

    Optional<Notification> NotificationGetById(Long id);
    List<Notification> NotificationGetAll();
}
