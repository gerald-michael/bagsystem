package com.gerald;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/login")

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals("gerald") && password.equals("root")) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            res.sendRedirect("home.jsp");
        } else {
            res.sendRedirect("index.jsp?message=" + "incorrect username or password");
        }
    }
}