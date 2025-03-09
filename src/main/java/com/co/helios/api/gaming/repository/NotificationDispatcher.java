package com.co.helios.api.gaming.repository;

import com.co.helios.api.gaming.model.dto.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationDispatcher {

    public void dispatch(Notification notification) {
        System.out.println("Sending Notification: " + notification);
    }
}
