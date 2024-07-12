package com.example.calendarapp.servlets;

import com.example.calendarapp.DAO.commentsDAO;
import com.example.calendarapp.calendar.Comment;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="loadCommentsServlet", value="/load-comments-servlet")
public class LoadCommentsServlet extends HttpServlet {
    public void init() {}


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventId = request.getParameter("eventId");
        List<Comment> comments = commentsDAO.getEventComments(Integer.parseInt(eventId));

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(comments);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    public void destroy() {}
}
