package com.authentication.web;

import com.authentication.bean.Group;
import com.authentication.bean.GroupPermissions;
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

@WebServlet("/removePermission")
public class RemovePermissionFromGroup extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int permissionId = Integer.parseInt(request.getParameter("permissionId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        GroupPermissions groupPermissions = new GroupPermissions();
        groupPermissions.setGroupId(groupId);
        groupPermissions.setPermissionId(permissionId);
        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            boolean result = authenticationDao.removePermissionFromGroup(groupPermissions);
            if (result) {
                response.sendRedirect("manage.jsp?message=" + "permission removed successfully");
            } else {
                response.sendRedirect("manage.jsp?error=" + "failed to remove permission");
            }
        } catch (Exception e) {
            response.sendRedirect("manage.jsp?error=" + "failed to remove permission");
        }
    }
}