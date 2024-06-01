package com.example.calendarapp.servlets;

import java.io.*;

import com.example.calendarapp.DAO.usersDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "registerServlet", value = "/register-servlet")
public class RegisterServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("usernameField");
        String password = request.getParameter("passwordField");
        String age = request.getParameter("ageField");
        int ageInt;

        if (username == null || password == null || age == null || username.isEmpty() || password.isEmpty() || age.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
            return;
        }

        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Age must be a valid integer.");
            return;
        }

        // check if user already exists
        if (usersDAO.searchUser(username)) {
            response.sendError(HttpServletResponse.SC_CONFLICT, "User already exists.");
            return;
        }

        // create user process
        try {
            boolean userCreated = usersDAO.createUser(username, password, ageInt);
            if (userCreated) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create user.");
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }



    public void destroy() {}
}
