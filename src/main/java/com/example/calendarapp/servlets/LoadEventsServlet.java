package com.example.calendarapp.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.calendarapp.DAO.eventsDAO;
import com.example.calendarapp.DAO.participantsDAO;
import com.example.calendarapp.calendar.Event;
import com.example.calendarapp.users.Participant;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loadEventsServlet", value = "/load-events-servlet")
public class LoadEventsServlet extends HttpServlet {

    public void init() {}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        List<Event> events = eventsDAO.getEventsForUser(username);
        List<Event> publicEvents = eventsDAO.getPublicEventsForUser(username);

        //merge the 2 lists
        List<Event> allUserEvents = new ArrayList<>();
        allUserEvents.addAll(events);
        allUserEvents.addAll(publicEvents);


        Gson gson = new Gson();
        String jsonResponse = gson.toJson(allUserEvents);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    public void destroy() {}
}