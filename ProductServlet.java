package com.xadmin.inventorymanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.inventorymanagement.bean.Product;
import com.xadmin.inventorymanagement.dao.ProductDao;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    private ProductDao productDao;
       

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		productDao = new ProductDao();
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
	switch(action)
	{
	case "/new":
		showNewForm(request, response);
		break;
		
	case "/insert":
		try {
			insertProduct(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
	case "/delete":
		deleteProduct(request, response);
		break;
		
	case "/edit":
		editProduct(request, response);
		break;
		
	case "/update":
		try {
			updateProduct(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
		default:
			listProduct(request, response);
			break;
		
	}
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("product.form.jsp");
		dispatcher.forward(request, response);
	}
	//insert product
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quatity = Integer.parseInt(request.getParameter("quatity"));
		Product newProduct = new Product(name, price, quatity);
		
		      productDao.insertProduct(newProduct);
		  response.sendRedirect("list");    
	}
	
	//delete product 
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			 productDao.deleteProduct(id);
		}catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("list");
		}
	
	//edit product
	private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		   
		    Product existingProduct;
		    try {
		    	existingProduct = productDao.selectProduct(id);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("product.form.jsp");
		    	request.setAttribute("product", existingProduct);
		    	dispatcher.forward(request, response);
		    } catch (Exception e) {
		    	//TODO Auto-generated catch block
		    	e.printStackTrace();
		    }
	}
	
	//update product
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
	
		int id = Integer.parseInt(request.getParameter("id"));
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	int quatity = Integer.parseInt(request.getParameter("quatity"));
	
	Product book = new Product(id, name, price, quatity);
	     productDao.updateProduct(book);
	response.sendRedirect("list");     
	}
	
	//default
	private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			List<Product>listProduct = productDao.selectAllproducts();
			request.setAttribute("listProduct", listProduct);
			RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
			dispatcher.forward(request, response);
		} catch	(Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	}
	
		
	


	


