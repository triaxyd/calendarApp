package com.example.calendarapp.servlets;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private String username;
    private String password;

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect( request.getContextPath()+"/jsp/home.jsp");
    }

    public void destroy() {}
}