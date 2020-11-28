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

@WebServlet("/deleteTransaction")
public class DeleteTransaction extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = new Transaction();
        transaction.setId(id);
        try {
            ProductDao productDao = new ProductDao();
            boolean result = productDao.deleteTransaction(transaction);
            if (result) {
                response.sendRedirect("transactions.jsp?message=" + "transaction deleted successfully");
            } else {
                response.sendRedirect("transactions.jsp?error=" + "failed to delete transaction");
            }
        } catch (Exception e) {
            response.sendRedirect("transactions.jsp?error=" + "failed to delete transaction");
        }
    }
}
