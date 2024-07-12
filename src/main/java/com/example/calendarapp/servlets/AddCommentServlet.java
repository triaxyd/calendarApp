package com.example.calendarapp.servlets;

import com.example.calendarapp.DAO.commentsDAO;
import com.example.calendarapp.calendar.Comment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddCommentServlet", value = "/add-comment-servlet")
public class AddCommentServlet extends HttpServlet {
    public void init() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String eventId = request.getParameter("eventId");
        String commentText = request.getParameter("comment");

        Comment newComment = new Comment(username, Integer.parseInt(eventId), commentText);

        // Add the comment to the database
        boolean isAdded = commentsDAO.addEventComment(newComment);

        // Prepare the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Create a JSON response indicating success or failure
        String jsonResponse = "{\"success\": " + isAdded + "}";
        out.print(jsonResponse);
        out.flush();
    }

    public void destroy() {}
}
