package com.co.helios.api.gaming.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserPreferences {

    private final Map<Integer, Map<String, Boolean>> preferences = new HashMap<>();

    public void setPreference(int userId, String category, boolean isEnabled) {
        preferences.putIfAbsent(userId, new HashMap<>());
        preferences.get(userId).put(category, isEnabled);
    }

    public boolean isNotificationEnabled(int userId, String category) {
        return preferences.getOrDefault(userId, new HashMap<>()).getOrDefault(category, true);
    }
}
