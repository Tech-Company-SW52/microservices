package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.entity.Notification;
import com.hiring.hiringservice.repository.INotificationRepository;
import com.hiring.hiringservice.service.INotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class NotificationServiceImpl implements INotificationService {

    @Autowired
    INotificationRepository notificationRepository;



    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Optional<Notification> NotificationGetById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> NotificationGetAll() {
        return notificationRepository.findAll();
    }

}
