package com.reports.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// import com.authentication.bean.User;
// import com.authentication.dao.AuthenticationDao;

public class ReportsDao {
    private static String url = "jdbc:mysql://localhost:3306/bagsystem";
    private static String dbUsername = "vypa";
    private static String dbPassword = "root";

    // products

    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    public static final String COLUMN_PRODUCT_ADDED_BY = "added_by";
    public static final String COLUMN_PRODUCT_UPDATED_BY = "updated_by";
    public static final String COLUMN_PRODUCT_IMAGE = "image";
    public static final String COLUMN_PRODUCT_DATE_UPDATED = "date_updated";
    public static final String COLUMN_PRODUCT_DATE_CREATED = "date_created";

    // stock
    public static final String TABLE_STOCK = "stock";
    public static final String COLUMN_STOCK_ID = "id";
    public static final String COLUMN_STOCK_PRODUCT_ID = "product_id";
    public static final String COLUMN_STOCK_QUANTITY = "quantity";
    public static final String COLUMN_STOCK_BUYING_PRICE = "buying_price";
    public static final String COLUMN_STOCK_SIZE = "size";
    public static final String COLUMN_STOCK_COLOR = "color";
    public static final String COLUMN_STOCK_ADDED_BY = "added_by";
    public static final String COLUMN_STOCK_UPDATED_BY = "updated_by";
    public static final String COLUMN_STOCK_DATE_UPDATED = "date_updated";
    public static final String COLUMN_STOCK_DATE_CREATED = "date_created";

    // TRANSACTIONS;
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_TRANSACTION_ID = "id";
    public static final String COLUMN_TRANSACTION_STOCK_ID = "stock_id";
    public static final String COLUMN_TRANSACTION_QUANTITY = "quantity";
    public static final String COLUMN_TRANSACTION_ADDED_BY = "added_by";
    public static final String COLUMN_TRANSACTION_UPDATED_BY = "updated_by";
    public static final String COLUMN_TRANSACTION_BUYING_PRICE = "buying_price";
    public static final String COLUMN_TRANSACTION_SELLING_PRICE = "selling_price";
    public static final String COLUMN_TRANSACTION_DATE_UPDATED = "date_updated";
    public static final String COLUMN_TRANSACTION_DATE_CREATED = "date_created";


    // queries    
    public static final String GET_STOCK_COUNT = "SELECT COUNT(*) FROM " + TABLE_STOCK ;/*+ " WHERE " + COLUMN_STOCK_ID + " =?";*/   
    public static final String GET_TRANSACTION_COUNT = "SELECT COUNT(*) FROM " +  TABLE_TRANSACTIONS ;
    public static final String GET_PRODUCT_COUNT = "SELECT COUNT(*) FROM " +  TABLE_PRODUCTS ;
   
    private Connection conn;
    PreparedStatement countStockPresent;
    PreparedStatement countProductPresent;
    PreparedStatement countTransactionPresent;
  
    public boolean open() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            countStockPresent = conn.prepareStatement(GET_STOCK_COUNT);
            countProductPresent = conn.prepareStatement(GET_TRANSACTION_COUNT, Statement.RETURN_GENERATED_KEYS);
            countTransactionPresent = conn.prepareStatement(GET_PRODUCT_COUNT , Statement.RETURN_GENERATED_KEYS);
            
            // getStock = conn.prepareStatement(GET_STOCK);
           
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (countStockPresent  != null) {
                countStockPresent .close();
            }
            if (countProductPresent != null) {
                countProductPresent.close();
            }
            if (countTransactionPresent != null) {
                countTransactionPresent.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection.");
        }
    }


    
    public int getCount(String table) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT COUNT(*) AS count FROM " + table;
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)) {
            int count = 0;
            while (results.next()) {
                count = results.getInt(1);
            }
            close();
            return count;
        } catch (SQLDataException e) {
            close();
            return -1;
        }
    }
    public int sums = getCount(TABLE_TRANSACTIONS);
}
    