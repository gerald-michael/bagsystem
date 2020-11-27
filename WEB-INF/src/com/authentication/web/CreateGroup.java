package com.authentication.login.web;

import com.authentication.bean.Group;
import com.authentication.bean.User;
import com.authentication.dao.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/create-group")
public class CreateGroup extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Group group = new Group();
        String name = request.getParameter("name");
        group.setName(name);
        
        User user = new User();
        HttpSession session = request.getSession();
        user.setUsername(session.getAttribute("username").toString());
        AuthenticationDao authenticationDao = new AuthenticationDao();
        try{
            user = authenticationDao.getUser(user);
            group.setCreatedBy(user.getId());
            int id = authenticationDao.createGroup(group);
            if(id != -1){
                response.sendRedirect("manage.jsp?message="+"group created successfully");
            }else{
                response.sendRedirect("manage.jsp?error="+"failed to create group");
            }
        }catch (Exception e){
            response.sendRedirect("manage.jsp?error="+"failed to create group");
        }
    }
}
