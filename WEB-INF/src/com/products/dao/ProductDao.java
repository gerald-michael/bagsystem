package com.products.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.products.bean.*;

public class ProductDao {
    private static String url = "jdbc:mysql://localhost:3306/bagsystem";
    private static String dbUsername = "vypa";
    private static String dbPassword = "root";

    // products

    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    public static final String COLUMN_PRODUCT_IMAGE = "image";
    public static final String COLUMN_PRODUCT_DATE_UPDATED = "date_updated";
    public static final String COLUMN_PRODUCT_DATE_CREATED = "date_created";

    // color

    public static final String TABLE_COLOR = "colors";
    public static final String COLUMN_COLOR_ID = "id";
    public static final String COLUMN_COLOR_NAME = "name";
    public static final String COLUMN_COLOR_QUANTITY = "quantity";
    public static final String COLUMN_COLOR_STOCK_ID = "stock_id";
    public static final String COLUMN_COLOR_IMAGE = "image";
    public static final String COLUMN_COLOR_DATE_UPDATED = "date_updated";
    public static final String COLUMN_COLOR_DATE_CREATED = "date_created";

    // size

    public static final String TABLE_SIZE = "sizes";
    public static final String COLUMN_SIZE_ID = "id";
    public static final String COLUMN_SIZE_NAME = "name";
    public static final String COLUMN_SIZE_QUANTITY = "quantity";
    public static final String COLUMN_SIZE_STOCK_ID = "stock_id";
    public static final String COLUMN_SIZE_IMAGE = "image";
    public static final String COLUMN_SIZE_DATE_UPDATED = "date_updated";
    public static final String COLUMN_SIZE_DATE_CREATED = "date_created";

    // stock
    public static final String TABLE_STOCK = "stock";
    public static final String COLUMN_STOCK_ID = "id";
    public static final String COLUMN_STOCK_PRODUCT_ID = "product_id";
    public static final String COLUMN_STOCK_QUANTITY = "quantity";
    public static final String COLUMN_STOCK_BUYING_PRICE = "buying_price";
    public static final String COLUMN_STOCK_DATE_UPDATED = "date_updated";
    public static final String COLUMN_STOCK_DATE_CREATED = "date_created";

    // TRANSACTIONS;
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_TRANSACTION_ID = "id";
    public static final String COLUMN_TRANSACTION_STOCK_ID = "stock_id";
    public static final String COLUMN_TRANSACTION_QUANTITY = "quantity";
    public static final String COLUMN_TRANSACTION_BUYING_PRICE = "buying_price";
    public static final String COLUMN_TRANSACTION_SELLING_PRICE = "selling_price";
    public static final String COLUMN_TRANSACTION_DATE_UPDATED = "date_updated";
    public static final String COLUMN_TRANSACTION_DATE_CREATED = "date_created";

    // stocklist view
    public static final String VIEW_STOCK_LIST = "stock_list";
    public static final String COLUMN_STOCK_LIST_PRODUCT_NAME = "product_name";
    public static final String COLUMN_STOCK_LIST_PRODUCT_DESCRIPTION = "product_description";
    public static final String COLUMN_STOCK_LIST_QUANTITY = "quantity";
    public static final String COLUMN_STOCK_LIST_BUYING_PRICE = "buying_price";
    public static final String COLUMN_STOCK_LIST_COLOR_NAME = "color_name";
    public static final String COLUMN_STOCK_LIST_COLOR_QUANTITY = "color_quantity";
    public static final String COLUMN_STOCK_LIST_SIZE_NAME = "size_name";
    public static final String COLUMN_STOCK_LIST_SIZE_QUANTITY = "size_quantity";
    public static final String COLUMN_STOCK_LIST_UPDATED = "date_updated";
    public static final String COLUMN_STOCK_LIST_DATE_CREATED = "date_created";

    // sort order

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // queries

    private Connection conn;

    public boolean open() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection.");
        }
    }

    public List<Product> queryProducts(int sortOrder) throws SQLException, ClassNotFoundException {
        open();

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_PRODUCTS);

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_PRODUCT_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {
            List<Product> products = new ArrayList<>();
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(COLUMN_PRODUCT_ID));
                product.setName(results.getString(COLUMN_PRODUCT_NAME));
                product.setDescription(results.getString(COLUMN_PRODUCT_DESCRIPTION));
                products.add(product);
            }
            close();
            return products;
        } catch (SQLException e) {
            System.out.println("Query failed" + e.getMessage());
            close();
            return null;
        }
    }

    public int getCount(String table) throws SQLException, ClassNotFoundException {
        open();
        String sql = "SELECT COUNT(*) AS count FROM" + table;
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)) {
            int count = results.getInt("count");
            close();
            return count;
        } catch (SQLException e) {
            close();
            return -1;
        }
    }

    public List<StockList> queryStockListView() throws SQLException, ClassNotFoundException {
        open();
        // StringBuilder sb = StringBuilder("SELECT * FROM " + VIEW_STOCK_LIST);

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM " + VIEW_STOCK_LIST)) {
            List<StockList> stockList = new ArrayList<>();
            while (results.next()) {
                StockList stock = new StockList();
                stock.setBuying_price(results.getDouble(COLUMN_STOCK_LIST_BUYING_PRICE));
                stock.setColor_name(results.getString(COLUMN_STOCK_LIST_COLOR_NAME));
                stockList.add(stock);
            }
            close();
            return stockList;
        } catch (SQLException e) {
            close();
            return null;
        }
    }
}
