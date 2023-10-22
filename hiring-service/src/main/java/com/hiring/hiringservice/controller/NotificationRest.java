package com.hiring.hiringservice.controller;


import com.hiring.hiringservice.service.NotificationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/notifications")
@Api(tags="Notification", value="Web Service RESTful of Notifications")
public class NotificationRest {
    private final NotificationService notificationService;

    public NotificationRest(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

}
