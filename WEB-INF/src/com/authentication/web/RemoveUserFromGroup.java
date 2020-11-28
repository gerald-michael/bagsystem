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

@WebServlet("/removeUser")
public class RemoveUserFromGroup extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        UserGroups userGroups = new UserGroups();
        userGroups.setGroupId(groupId);
        userGroups.setUserId(userId);
        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            boolean result = authenticationDao.removeUserFromGroup(userGroups);
            if (result) {
                response.sendRedirect("manage.jsp?message=" + "user removed successfully");
            } else {
                response.sendRedirect("manage.jsp?error=" + "failed to remove user");
            }
        } catch (Exception e) {
            response.sendRedirect("manage.jsp?error=" + "failed to remove user");
        }
    }
}
