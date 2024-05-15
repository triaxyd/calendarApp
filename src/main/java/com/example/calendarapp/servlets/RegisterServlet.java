package com.example.calendarapp.servlets;

import java.io.*;

import com.example.calendarapp.DAO.usersDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));

        // if user already exists return false
        if (usersDAO.searchUser(username)) return;

        // create user process




    }

    public void destroy() {}
}
