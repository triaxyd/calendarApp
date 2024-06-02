package com.example.calendarapp.calendar;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Event {
    private int eventId;
    private String eventName;
    private Timestamp eventDate;
    private int eventDuration;
    private String eventDescription;
    private final String creatorUsername;
    private boolean isPublic;

    public Event(String eventName, Timestamp eventDate, int eventDuration, String eventDescription, String creatorUsername, boolean isPublic) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDuration = eventDuration;
        this.eventDescription = eventDescription;
        this.creatorUsername = creatorUsername;
        this.isPublic = isPublic;
    }

    public Event(int eventId, String eventName, Timestamp eventDate, int eventDuration, String eventDescription, String creatorUsername, boolean isPublic) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDuration = eventDuration;
        this.eventDescription = eventDescription;
        this.creatorUsername = creatorUsername;
        this.isPublic = isPublic;
    }

    public int getEventId() {
        return this.eventId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public Timestamp getEventDate() {
        return this.eventDate;
    }

    public int getEventDuration() {
        return this.eventDuration;
    }

    public String getEventDescription() {
        return this.eventDescription;
    }

    public String getCreatorUsername() {
        return this.creatorUsername;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }


}
