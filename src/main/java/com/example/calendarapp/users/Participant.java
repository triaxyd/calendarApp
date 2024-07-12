package com.example.calendarapp.users;

public class Participant {
    private String username;
    private int eventId;

    public Participant(String username, int eventId) {
        this.username = username;
        this.eventId = eventId;
    }

    public String getUsername() {
        return this.username;
    }

    public int getEventId() {
        return this.eventId;
    }
}
