package com.products.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import com.authentication.bean.User;
import com.authentication.dao.AuthenticationDao;
import com.products.bean.*;
import com.products.dao.*;
@WebServlet("/createstock")
public class StockCreateServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("called");
    }
}
