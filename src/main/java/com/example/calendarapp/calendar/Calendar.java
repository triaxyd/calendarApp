package com.example.calendarapp.calendar;
import java.util.LinkedList;
import java.util.List;

public class Calendar {
    private LinkedList<Event> events;

    public Calendar() {}

    public Calendar(List<Event> events) {
        this.events = new LinkedList<>(events);
    }

    public LinkedList<Event> getEvents() {
        return this.events;
    }
}
