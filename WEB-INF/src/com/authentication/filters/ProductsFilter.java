package com.authentication.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.authentication.bean.UserGroupPermission;
import com.authentication.dao.AuthenticationDao;
@WebFilter("/productFilter")
public class ProductsFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = response.getWriter();
        AuthenticationDao authenticationDao = new AuthenticationDao();
        UserGroupPermission userGroupPermission = new UserGroupPermission();
        HttpSession session = req.getSession();
        String username = session.getAttribute("username").toString();
        userGroupPermission.setUsername(username);
        List<UserGroupPermission> userGroupPermissions = authenticationDao
                .queryUserGroupPermissionView(userGroupPermission);
        List<String> permissions = new ArrayList<>();
        for (UserGroupPermission user_group_permissions : userGroupPermissions) {
            permissions.add(user_group_permissions.getPermissionName());
        }
        if (permissions.contains("all")||permissions.contains("addProducts")||permissions.contains("updateProducts")||permissions.contains("viewProducts")||permissions.contains("deleteProducts")) {
            chain.doFilter(request, response);
        }else{
            res.sendRedirect("error.jsp");
        }
    }

    public void destroy() {
        // close any resources here
    }
}
