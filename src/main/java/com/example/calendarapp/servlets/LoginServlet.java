package com.example.calendarapp.servlets;

import java.io.*;

import com.example.calendarapp.DAO.usersDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("usernameField");
        String password = request.getParameter("passwordField");

        // call userDAO to authenticate the user

        if (usersDAO.validateUser(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            //authentication process
            response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");
        } else {
            response.sendRedirect(request.getContextPath());
        }
    }

    public void destroy() {}
}