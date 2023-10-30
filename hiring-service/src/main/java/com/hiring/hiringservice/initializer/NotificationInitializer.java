package com.hiring.hiringservice.initializer;

import com.hiring.hiringservice.entity.Notification;
import com.hiring.hiringservice.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

public class NotificationInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (notificationRepository.count() == 0) {
            List<Notification> notificationList = List.of(
                    new Notification(0L, true),
                    new Notification(0L, false)
            );

            notificationRepository.saveAll(notificationList);
        }
    }
}
