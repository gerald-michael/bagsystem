package com.products.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
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

public class TransactionCreateServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int stock_id = Integer.parseInt(request.getParameter("stock"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double sellingPrice = Double.parseDouble(request.getParameter("sellingprice"));
        Transaction transaction = new Transaction();
        transaction.setQuantity(quantity);
        transaction.setSelling_price(sellingPrice);
        transaction.setStock_id(stock_id);
        User user = new User();
        HttpSession session = request.getSession();
        user.setUsername(session.getAttribute("username").toString());
        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            user = authenticationDao.getUser(user);
            transaction.setAdded_by(user.getId());
            ProductDao productDao = new ProductDao();
            productDao.createTransaction(transaction);
            response.sendRedirect("transactions.jsp?message=" + "transaction created succesfully");
        } catch (Exception e) {
            response.sendRedirect("transactions.jsp?error=" + "failed to create transaction");
        }
    }
}