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

public class StockCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double buyingPrice = Double.parseDouble(request.getParameter("buyingprice"));
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        User user = new User();
        HttpSession session = request.getSession();
        user.setUsername(session.getAttribute("username").toString());
        Stock stock = new Stock();
        stock.setColor(color);
        stock.setQuantity(quantity);
        stock.setBuying_price(buyingPrice);
        stock.setSize(size);
        Product product = new Product();
        product.setName(productName);
        try {
            AuthenticationDao authenticationDao = new AuthenticationDao();
            user = authenticationDao.getUser(user);
            stock.setAdded_by(user.getId());
            ProductDao productDao = new ProductDao();
            productDao.createStock(stock, product);
            response.sendRedirect("stock.jsp?message=" + "stock added succesfully");
        } catch (Exception e) {
            response.sendRedirect("stock.jsp?error=" + "failed to add stock");
        }
    }
}
