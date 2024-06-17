package com.example.calendarapp.servlets;

import com.example.calendarapp.DAO.eventsDAO;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "deleteEventServlet", value = "/delete-event-servlet")
public class DeleteEventServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int eventId = Integer.parseInt(request.getParameter("eventId"));

        boolean eventDeleted = eventsDAO.deleteEvent(eventId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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
