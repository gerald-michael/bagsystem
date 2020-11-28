package com.authentication.web;

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

@WebServlet("/assignGroup")

public class AssignGroupPermission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int permissionId = Integer.parseInt(request.getParameter("permissionId"));

        GroupPermissions groupPermissions = new GroupPermissions();
        groupPermissions.setGroupId(groupId);
        groupPermissions.setPermissionId(permissionId);

        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            int id = authenticationDao.assignPermissionsToGroups(groupPermissions);
            if (id != -1) {
                response.sendRedirect("manage.jsp?message=" + "Permission assigned successfully");
            } else {
                response.sendRedirect("manage.jsp?error=" + "Failed to assign permission1");
            }
        } catch (Exception e) {
            response.sendRedirect("manage.jsp?error=" + "Failed to assign permission2");
        }

    }
}
