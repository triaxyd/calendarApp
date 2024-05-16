package com.example.calendarapp.users;

public class Participant {
    private int username;
    private int eventId;

    public Participant(int username, int eventId) {
        this.username = username;
        this.eventId = eventId;
    }

    public int getUsername() {
        return this.username;
    }

    public int getEventId() {
        return this.eventId;
    }
}
