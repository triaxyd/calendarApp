package com.example.calendarapp.servlets;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private String username;
    private String password;
    private int age;

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getAttribute("usernameField");
        request.getAttribute("passwordField");
        request.getAttribute("ageField");
        username = request.getParameter("username");
        password = request.getParameter("password");
        age = Integer.parseInt(request.getParameter("age"));


    }

    public void destroy() {}
}
