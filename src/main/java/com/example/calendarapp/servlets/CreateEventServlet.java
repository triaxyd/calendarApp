package com.example.calendarapp.servlets;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.example.calendarapp.DAO.eventsDAO;
import com.example.calendarapp.DAO.participantsDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "createEventServlet", value = "/create-event-servlet")
public class CreateEventServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eventName = request.getParameter("eventName");
        String eventDateString = request.getParameter("eventDate");
        String eventDurationString = request.getParameter("eventDuration");
        String eventDescription = request.getParameter("eventDescription");
        String creatorUsername = request.getParameter("creatorUsername");
        String isPublicString = request.getParameter("eventIsPublic");
        String eventParticipantsString = request.getParameter("eventParticipants");

        Timestamp eventDate;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

        try {
            eventDate = new Timestamp(formatter.parse(eventDateString).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int eventDuration = Integer.parseInt(eventDurationString);
        boolean isPublic = Boolean.parseBoolean(isPublicString);
        //String[] participants = eventParticipantsString.split(",");

        //eventsDAO access the database and create new event tuple,
        //return the eventId created which is auto incremented and >=0
        int eventIdCreated = eventsDAO.addEvent(eventName, eventDate, eventDuration, eventDescription, creatorUsername, isPublic);

        //if the event was created successfully
        //participantDAO access the database and create a new participant tuple for creator
        if (eventIdCreated >= 0){
            participantsDAO.addParticipantToEvent(eventIdCreated, creatorUsername);
        }

        String[] participantUsernames;
        if(isPublic && eventParticipantsString!=null && !eventParticipantsString.isEmpty()){
            participantUsernames = eventParticipantsString.split(",");
            for (String participantUsername : participantUsernames) {
                //call participantsDAO to add other participants
                participantsDAO.addParticipantToEvent(eventIdCreated, participantUsername.trim());
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonResponse = new JsonObject();


        if (eventIdCreated >= 0) {
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Created Event " + eventIdCreated);
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Event could not be created" + eventIdCreated);
        }

        response.getWriter().write(jsonResponse.toString());
    }

    public void destroy() {}
}