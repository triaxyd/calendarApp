package com.example.calendarapp.servlets;

import java.io.*;

import com.example.calendarapp.DAO.usersDAO;
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
        request.getParameter("usernameField");
        request.getParameter("passwordField");
        request.getParameter("ageField");

        username = request.getParameter("username");
        password = request.getParameter("password");
        age = Integer.parseInt(request.getParameter("age"));

        // if user already exists return false
        if (usersDAO.searchUser(username)) return;

        // create user process




    }

    public void destroy() {}
}
