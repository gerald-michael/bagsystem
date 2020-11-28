package com.authentication.web;

import com.authentication.bean.User;
import com.authentication.dao.AuthenticationDao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm");

		User user = new User();
		user.setConfirmPassword(confirmPassword);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPassword(password);
		user.setUsername(username);
		PrintWriter out = response.getWriter();
		if (user.validatePassword()) {
			AuthenticationDao authenticate = new AuthenticationDao();
			try {
				int id = authenticate.createUser(user);
				if (id != -1) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					response.sendRedirect("index.jsp");
				} else {
					response.sendRedirect("auth.jsp");
				}
			} catch (Exception e) {
				response.sendRedirect("auth.jsp");
			}
		} else {
			out.println("no pasword: " + user.getPassword() + " confirm password: " + user.getConfirmPassword());
		}
	}
}