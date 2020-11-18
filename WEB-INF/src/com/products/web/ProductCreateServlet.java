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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ProductCreateServlet extends HttpServlet {
    public static final String UPLOAD_DIR = "images";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part part = request.getPart("image");
        String fileName = extractFileName(part);
        String applicationPath = getServletContext().getRealPath("");
        String uploadPath = applicationPath + UPLOAD_DIR;

        File fileUploadDirectory = new File(uploadPath);
        if (!fileUploadDirectory.exists()) {
            fileUploadDirectory.mkdirs();
        }
        PrintWriter out = response.getWriter();
        String savePath = uploadPath + File.separator + fileName;
        out.println("savePath: " + savePath);
        out.println(uploadPath);
        String sRootPath = new File(savePath).getAbsolutePath();
        out.println("sRootPath: " + sRootPath);
        part.write(savePath + File.separator);
        File fileSaveDir1 = new File(savePath);
        // HttpSession session = request.getSession();
        // User user = new User();
        // user.setUsername(session.getAttribute("username").toString());
        // AuthenticationDao authenticationDao = new AuthenticationDao();
        // user = authenticationDao.getUser(user);

        // Product product = new Product();
        // product.setName(name);
        // product.setDescription(description);
        // product.setImageUri("pic.jpg");
        // product.setAdded_by(user.getId());

        // ProductDao productDao = new ProductDao();
        // try {
        // int id = productDao.insertProduct(product);
        // if (id != -1) {
        // response.sendRedirect("products.jsp?message=" + "product created
        // successfully");
        // } else {
        // response.sendRedirect("products.jsp?error=" + "failed to create product");

        // }
        // } catch (Exception e) {
        // response.sendRedirect("products.jsp?error=" + "failed to create product");
        // }
    }

    private String extractFileName(Part part) {// This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}