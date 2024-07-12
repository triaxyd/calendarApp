package com.example.calendarapp.servlets;

import com.example.calendarapp.DAO.eventsDAO;
import com.example.calendarapp.DAO.participantsDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "editEventServlet", value = "/edit-event-servlet")
public class EditEventServlet extends HttpServlet{
    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String eventIdString = request.getParameter("eventId");
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
        int eventId= Integer.parseInt(eventIdString);
        int eventDuration = Integer.parseInt(eventDurationString);
        boolean isPublic = Boolean.parseBoolean(isPublicString);
        //String[] participants = eventParticipantsString.split(",");

        //return the eventId created which is auto incremented and >=0

        //if the event is found successfully
        eventsDAO.editEvent(eventId,eventName, eventDate,eventDuration,eventDescription,creatorUsername,isPublic);
        participantsDAO.deleteParticipantsFromEvent(eventId);
        if (eventId>= 0){
            participantsDAO.addParticipantToEvent(eventId, creatorUsername);
        }

        String[] participantUsernames;
        if(isPublic && eventParticipantsString!=null && !eventParticipantsString.isEmpty()){
            participantUsernames = eventParticipantsString.split(",");
            for (String participantUsername : participantUsernames) {
                //call participantsDAO to add other participants
                participantsDAO.addParticipantToEvent(eventId, participantUsername.trim());
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonResponse = new JsonObject();


        if (eventId >= 0) {
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Edited Event " + eventId);
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Event could not be edited" + eventId);
        }

        response.getWriter().write(jsonResponse.toString());
    }

    public void destroy() {}


}

