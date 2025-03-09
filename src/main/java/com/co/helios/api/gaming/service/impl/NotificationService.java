package com.co.helios.api.gaming.service.impl;

import com.co.helios.api.gaming.model.dto.Notification;
import com.co.helios.api.gaming.repository.NotificationDispatcher;
import com.co.helios.api.gaming.repository.UserPreferences;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final UserPreferences userPreferences;
    private final NotificationDispatcher dispatcher;

    public NotificationService(UserPreferences userPreferences, NotificationDispatcher dispatcher) {
        this.userPreferences = userPreferences;
        this.dispatcher = dispatcher;
    }

    public void sendNotification(int userId, String category, String message) {
        if (userPreferences.isNotificationEnabled(userId, category)) {
            Notification notification = new Notification(userId, message);
            dispatcher.dispatch(notification);
        }
    }
}
