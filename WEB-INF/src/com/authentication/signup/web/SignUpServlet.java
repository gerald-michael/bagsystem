package com.authentication.signup.web;

import com.authentication.signup.bean.*;
import com.authentication.signup.dao.*;
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
	private static final long serialVersionUID = 1L;
	private SignUpDao signUpDao;

	public void init() {
		signUpDao = new SignUpDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm");

		PrintWriter out = response.getWriter();
		SignUpBean signUpBean = new SignUpBean(username, firstName, lastName, password, confirmPassword);

		try {
			// employeeDao.registerEmployee(employee);
			int result = signUpDao.register(signUpBean);
			out.println("result" + result);

		} catch (Exception e) {
			// // TODO Auto-generated catch block
			out.println("error");
			e.printStackTrace();
		}

		// response.sendRedirect("employeedetails.jsp");
	}
}