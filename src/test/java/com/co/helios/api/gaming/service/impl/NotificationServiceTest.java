package com.co.helios.api.gaming.service.impl;

import com.co.helios.api.gaming.model.dto.Notification;
import com.co.helios.api.gaming.repository.NotificationDispatcher;
import com.co.helios.api.gaming.repository.UserPreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    private UserPreferences userPreferences;
    private NotificationDispatcher dispatcher;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        userPreferences = mock(UserPreferences.class);
        dispatcher = mock(NotificationDispatcher.class);
        notificationService = new NotificationService(userPreferences, dispatcher);
    }

    @Test
    void testSendNotification_WhenEnabled() {
        // Given
        int userId = 1;
        String category = "Game Events";
        String message = "Congratulations! You've reached level 10!";

        when(userPreferences.isNotificationEnabled(userId, category)).thenReturn(true);

        // When
        notificationService.sendNotification(userId, category, message);

        // Then
        ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(dispatcher, times(1)).dispatch(notificationCaptor.capture());

        Notification capturedNotification = notificationCaptor.getValue();
        assertEquals(userId, capturedNotification.getUserId());
        assertEquals(message, capturedNotification.getMessage());
    }

    @Test
    void testSendNotification_WhenDisabled() {
        // Given
        int userId = 2;
        String category = "Social Events";
        String message = "You have a new friend request.";

        when(userPreferences.isNotificationEnabled(userId, category)).thenReturn(false);

        // When
        notificationService.sendNotification(userId, category, message);

        // Then
        verify(dispatcher, never()).dispatch(any(Notification.class));
    }
}
