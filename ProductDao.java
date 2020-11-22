package com.xadmin.inventorymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.inventorymanagement.bean.Product;
import com.xadmin.inventorymanagement.model.Products;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table products in the database.
 * 
 *
 */

public class ProductDao { 
	
		private String jdbcURL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
		private String jdbcUsername = "root";
		private String jdbcPassword = "rootpasswordgiven";
		private String jdbcDriver = "com.mysql.jdbc.Driver";

		private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (name, price, quatity) VALUES "
				+ " (?, ?, ?);";

		private static final String SELECT_PRODUCT_BY_ID = "select id,name,price,quatity from products where id =?";
		private static final String SELECT_ALL_PRODUCTS = "select * from products";
		private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
		private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,price = ?, quatity =? where id = ?;";

		public ProductDao() {
		}

		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName(jdbcDriver);
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		}

		public void insertProduct(Product product) throws SQLException {
			System.out.println(INSERT_PRODUCTS_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
				preparedStatement.setString(1, product.getName());
				preparedStatement.setInt(2, product.getPrice());
				preparedStatement.setInt(3, product.getQuatity());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}

		public Product selectProduct(int id) {
			Product product = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
					// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String name = rs.getString("name");
					int price = rs.getInt("price");
					int quatity = rs.getInt("quatity");
					product = new Product(id, name, price, quatity);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return product;
		}

		public List<Product> selectAllproducts() {

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Product> products = new ArrayList<>();
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();

					// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int price = rs.getInt("price");
					int quatity = rs.getInt("quatity");
					products.add(new Product(id, name, price, quatity));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return products;
		}

		public boolean deleteUser(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}

		public boolean updateProduct(Product product) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
				System.out.println("updated Products:"+statement);
				statement.setString(1, product.getName());
				statement.setInt(2, product.getPrice());
				statement.setInt(3, product.getQuatity());
				statement.setInt(4, product.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}

		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}

		public void deleteProduct(int id) {
			// TODO Auto-generated method stub
			
		}

	}

