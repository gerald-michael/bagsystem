package com.authentication.login.web;

import com.authentication.bean.User;
import com.authentication.dao.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User user = new User();
        AuthenticationDao authenticationDao = new AuthenticationDao();
        user.setUsername(username);
        user.setPassword(password);

        try {
            if (authenticationDao.login(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("index.jsp");
            } else {
                HttpSession session = request.getSession();
                response.sendRedirect("auth.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}