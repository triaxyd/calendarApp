package com.example.calendarapp.calendar;

public class Comment {
    private final String username;
    private final int eventId;
    private String comment;

    public Comment(String creatorUsername, int eventId, String comment) {
        this.username = creatorUsername;
        this.eventId = eventId;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public int getEventId() {
        return eventId;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
