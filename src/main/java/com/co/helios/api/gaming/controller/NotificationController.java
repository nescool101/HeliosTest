package com.co.helios.api.gaming.controller;

import com.co.helios.api.gaming.service.impl.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping("/game")
    public String triggerGameEvent(@RequestParam int userId,
                                   @RequestParam String eventType,
                                   @RequestParam(required = false) String param) {
        switch (eventType) {
            case "levelUp":
                notificationService.sendNotification(userId, "Game Events",
                        "Congratulations! You've reached level " + param + "!");
                break;
            case "itemAcquired":
                notificationService.sendNotification(userId, "Game Events",
                        "You have acquired the legendary " + param + "!");
                break;
            default:
                return "Unknown game event.";
        }
        return "Game event notification sent.";
    }

    @PostMapping("/social")
    public String triggerSocialEvent(@RequestParam int userId1,
                                     @RequestParam int userId2,
                                     @RequestParam String eventType) {
        switch (eventType) {
            case "friendRequestSent":
                notificationService.sendNotification(userId2, "Social Events",
                        "Player " + userId1 + " has sent you a friend request.");
                break;
            case "friendRequestAccepted":
                notificationService.sendNotification(userId1, "Social Events",
                        "Player " + userId2 + " has accepted your friend request.");
                break;
            default:
                return "Unknown social event.";
        }
        return "Social event notification sent.";
    }
}