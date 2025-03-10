package com.co.helios.api.gaming.controller;

import com.co.helios.api.gaming.service.impl.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        Mockito.reset(notificationService);
    }

    @Test
    void testTriggerGameEvent_LevelUp() throws Exception {
        mockMvc.perform(post("/notifications/game")
                        .param("userId", "1")
                        .param("eventType", "levelUp")
                        .param("param", "20"))
                .andExpect(status().isOk())
                .andExpect(content().string("Game event notification sent."));

        verify(notificationService, times(1)).sendNotification(1, "Game Events",
                "Congratulations! You've reached level 20!");
    }

    @Test
    void testTriggerGameEvent_ItemAcquired() throws Exception {
        mockMvc.perform(post("/notifications/game")
                        .param("userId", "2")
                        .param("eventType", "itemAcquired")
                        .param("param", "Excalibur"))
                .andExpect(status().isOk())
                .andExpect(content().string("Game event notification sent."));

        verify(notificationService, times(1)).sendNotification(2, "Game Events",
                "You have acquired the legendary Excalibur!");
    }

    @Test
    void testTriggerSocialEvent_FriendRequestSent() throws Exception {
        mockMvc.perform(post("/notifications/social")
                        .param("userId1", "3")
                        .param("userId2", "1")
                        .param("eventType", "friendRequestSent"))
                .andExpect(status().isOk())
                .andExpect(content().string("Social event notification sent."));

        verify(notificationService, times(1)).sendNotification(1, "Social Events",
                "Player 3 has sent you a friend request.");
    }

    @Test
    void testTriggerSocialEvent_FriendRequestAccepted() throws Exception {
        mockMvc.perform(post("/notifications/social")
                        .param("userId1", "1")
                        .param("userId2", "3")
                        .param("eventType", "friendRequestAccepted"))
                .andExpect(status().isOk())
                .andExpect(content().string("Social event notification sent."));

        verify(notificationService, times(1)).sendNotification(1, "Social Events",
                "Player 3 has accepted your friend request.");
    }

    @Test
    void testTriggerGameEvent_UnknownType() throws Exception {
        mockMvc.perform(post("/notifications/game")
                        .param("userId", "1")
                        .param("eventType", "unknownType"))
                .andExpect(status().isOk())
                .andExpect(content().string("Unknown game event."));

        verify(notificationService, never()).sendNotification(anyInt(), anyString(), anyString());
    }

    @Test
    void testTriggerSocialEvent_UnknownType() throws Exception {
        mockMvc.perform(post("/notifications/social")
                        .param("userId1", "1")
                        .param("userId2", "2")
                        .param("eventType", "invalidEvent"))
                .andExpect(status().isOk())
                .andExpect(content().string("Unknown social event."));

        verify(notificationService, never()).sendNotification(anyInt(), anyString(), anyString());
    }
}
