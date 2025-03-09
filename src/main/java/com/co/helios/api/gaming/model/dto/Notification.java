package com.co.helios.api.gaming.model.dto;

public class Notification {

    private final int userId;
    private final String message;

    public Notification(int userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Notification{userId=" + userId + ", message='" + message + "'}";
    }
}
