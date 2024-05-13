package com.example.calendarapp.servlets;

import java.io.*;


import com.example.calendarapp.DAO.usersDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private String username;
    private String password;

    public void init() {}


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // call userDAO to authenticate the user
        username = request.getParameter("usernameField");
        password = request.getParameter("passwordField");

        if(usersDAO.validateUser(username,password)){
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);

            //authentication process

            response.sendRedirect( request.getContextPath()+"/jsp/home.jsp");
        }
    }

    public void destroy() {}
}