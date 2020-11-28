package com.authentication.web;

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

@WebServlet("/assignUser")
public class AssignUserGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        UserGroups userGroups = new UserGroups();
        userGroups.setGroupId(groupId);
        userGroups.setUserId(userId);
        PrintWriter out = response.getWriter();
        out.println(userGroups.getGroupId()+":"+ userGroups.getUserId());
        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            int id = authenticationDao.assignUsersToGroups(userGroups);
            if (id != -1) {
                response.sendRedirect("manage.jsp?message=" + "Successfully added user to group");
            } else {
                response.sendRedirect("manage.jsp?error=" + "Failed to add user to group1");
            }
        } catch (Exception e) {
            response.sendRedirect("manage.jsp?error=" + "Failed to add user to group2");
        }
    }
}
