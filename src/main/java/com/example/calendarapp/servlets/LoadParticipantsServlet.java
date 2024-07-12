package com.example.calendarapp.servlets;

import java.io.*;
import java.util.List;


import com.example.calendarapp.DAO.participantsDAO;

import com.example.calendarapp.users.Participant;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loadParticipantsServlet", value = "/load-participants-servlet")
public class LoadParticipantsServlet extends HttpServlet {

    public void init() {}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        List<Participant> participants = participantsDAO.getEventParticipants(Integer.parseInt(eventId));

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(participants);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    public void destroy() {}
}