package com.products.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.products.bean.*;
import com.products.dao.*;

@WebServlet("/create")
public class ProductCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao;

    public void init() {
        productDao = new ProductDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        PrintWriter out = response.getWriter();
        // Product product = new Product(name, description);
        try {
            // List<Product> result = productDao.queryProducts(productDao.ORDER_BY_NONE);
            // out.println(result.size());
            int count = productDao.getCount(productDao.TABLE_PRODUCTS);
            out.println("total records: " + count);

            // List <StockList> stockLists = productDao.queryStockListView();
            // out.println(stockLists.size());
        } catch (Exception e) {
            out.println("error" + e.getMessage());
        }
    }
}