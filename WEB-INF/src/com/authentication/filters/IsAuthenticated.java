package com.authentication.filters;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebFilter("/IsAuthenticated")
public class IsAuthenticated implements Filter {

    private FilterConfig filterConfig = null;

    public void init(FilterConfig fConfig) throws ServletException {
        this.filterConfig = fConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        PrintWriter out = response.getWriter();
        String[] uriArray = req.getRequestURI().split("/");
        String uri = uriArray[uriArray.length - 1];
        String excludes = filterConfig.getInitParameter("excludes");
        if (excludes.contains(uri)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();
            if (session.getAttribute("username") == null) {
                res.sendRedirect("auth.jsp");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void destroy() {
        // close any resources here
    }

}