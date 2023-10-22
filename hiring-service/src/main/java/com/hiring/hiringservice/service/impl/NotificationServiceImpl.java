package com.hiring.hiringservice.service.impl;

import com.hiring.hiringservice.entity.Notification;
import com.hiring.hiringservice.repository.NotificationRepository;
import com.hiring.hiringservice.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // Solo servicios de lectura
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) throws Exception {
        notificationRepository.deleteById(id);
    }

    @Override
    public Optional<Notification> getById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }
}
