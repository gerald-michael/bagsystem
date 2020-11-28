package com.authentication.web;

import com.authentication.bean.Group;
import com.authentication.bean.User;
import com.authentication.bean.UserGroups;
import com.authentication.dao.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("name");
        Group group = new Group();
        group.setName(name);
        try{
            AuthenticationDao authenticationDao = new AuthenticationDao();
            boolean result = authenticationDao.deleteGroup(group);
            if(result){
                response.sendRedirect("manage.jsp?message="+"group deleted successfully");
            }else{
                response.sendRedirect("manage.jsp?error="+"failed to delete group");
            }
        }catch(Exception e){
            response.sendRedirect("manage.jsp?error="+"failed to delete group");
        }
    }   
}
