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

@WebServlet("/create")
public class ProductCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();
        User user = new User();
        user.setUsername(session.getAttribute("username").toString());
        AuthenticationDao authenticationDao = new AuthenticationDao();
        user = authenticationDao.getUser(user);

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUri("pic.jpg");
        product.setAdded_by(user.getId());
        ProductDao productDao = new ProductDao();
        try {
            int id = productDao.createProduct(product);
            if (id != -1) {
                response.sendRedirect("product.jsp?message=" + "product created successfully");
            } else {
                response.sendRedirect("product.jsp?error=" + "failed to create product");

            }
        } catch (Exception e) {
            response.sendRedirect("product.jsp?error=" + "failed to create product");
        }
    }
}