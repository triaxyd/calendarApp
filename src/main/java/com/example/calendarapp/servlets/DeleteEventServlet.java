package com.example.calendarapp.servlets;

import com.example.calendarapp.DAO.participantsDAO;

import com.example.calendarapp.DAO.eventsDAO;
import com.example.calendarapp.calendar.Event;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;




@WebServlet(name = "deleteEventServlet", value = "/delete-event-servlet")
public class DeleteEventServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        String username = request.getParameter("username");

        Event event = eventsDAO.getEvent(eventId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject jsonObject = new JsonObject();

        if(!Objects.equals(event.getCreatorUsername(), username)){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            jsonObject.addProperty("message", "You are not allowed to delete this event");
            response.getWriter().write(jsonObject.toString());

        }

        //boolean participantsDeleted = participantsDAO.deleteEventParticipants()

        //WE FIRST NEED TO DELETE FROM PARTICIPANT AND COMMENTS

        boolean participantDeleted = participantsDAO.deleteParticipantsFromEvent(eventId);
        boolean eventDeleted = eventsDAO.deleteEvent(eventId);

        response.setContentType("application/json");


        JsonObject jsonResponse = new JsonObject();
        if (eventDeleted) {
            jsonResponse.addProperty("success", true);
            jsonResponse.addProperty("message", "Event Deleted successfully");
        } else {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Event could not be deleted");
        }

        response.getWriter().write(jsonResponse.toString());
    }

    public void destroy() {}

}
