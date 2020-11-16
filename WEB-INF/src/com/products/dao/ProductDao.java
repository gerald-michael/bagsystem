package com.products.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.products.bean.*;

import jdk.internal.org.jline.utils.Colors;

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
    public static final String COLUMN_TRANSACTION_COLOR_ID = "color_id";
    public static final String COLUMN_TRANSACTION_SIZE_ID = "size_id";
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
    public static final String CHECK_IF_PRODUCT_EXISTS = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCT_NAME + " = ?";
    public static final String CHECK_IF_COLOR_EXISTS = "SELECT * FROM "+ TABLE_COLOR + " WHERE " + COLUMN_COLOR_NAME + " = ? AND "+ COLUMN_COLOR_STOCK_ID + " = ?";
    public static final String CHECK_IF_SIZE_EXISTS = "SELECT * FROM "+ TABLE_SIZE + " WHERE " + COLUMN_SIZE_NAME + " = ? AND "+ COLUMN_SIZE_STOCK_ID + " = ?";
    public static final String QUERY_VIEW_STOCK_LIST = "SELECT * FROM " + VIEW_STOCK_LIST + " WHERE " + COLUMN_STOCK_LIST_PRODUCT_NAME + " = ?";
    public static final String INSERT_PRODUCTS = "INSERT INTO " + TABLE_PRODUCTS + " (" + COLUMN_PRODUCT_NAME + ", "+ COLUMN_PRODUCT_DESCRIPTION + ") VALUES (?,?)";
    public static final String INSERT_COLOR = "INSERT INTO " + TABLE_COLOR + " (" + COLUMN_COLOR_STOCK_ID + ", "+COLUMN_COLOR_NAME + ", "+ COLUMN_COLOR_QUANTITY+ ") VALUES (?,?,?)";
    public static final String INSERT_SIZE = "INSERT INTO " + TABLE_SIZE + " (" + COLUMN_SIZE_STOCK_ID + ", "+COLUMN_SIZE_NAME + ", "+ COLUMN_SIZE_QUANTITY+ ") VALUES (?,?,?)";
    public static final String INSERT_STOCK = "INSERT INTO " + TABLE_STOCK + " ("+ COLUMN_STOCK_PRODUCT_ID + ", " + COLUMN_STOCK_QUANTITY + ", " + COLUMN_STOCK_BUYING_PRICE + ") VALUES (?,?,?)";
    public static final String INSERT_TRANSACTION = "INSET INTO " + TABLE_TRANSACTIONS + " (" + COLUMN_TRANSACTION_STOCK_ID + ", " + COLUMN_TRANSACTION_COLOR_ID + ", " + COLUMN_TRANSACTION_SIZE_ID + ", " + COLUMN_TRANSACTION_QUANTITY + ", " + COLUMN_TRANSACTION_BUYING_PRICE + ", " + COLUMN_TRANSACTION_SELLING_PRICE + ") VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_PRODUCT = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_PRODUCT_NAME + " = ? , " + COLUMN_PRODUCT_DESCRIPTION + " = ? WHERE " + COLUMN_PRODUCT_ID + " = ?";
    public static final String QUERY_COLORS = "SELECT * FROM " + TABLE_COLOR + " WHERE " + COLUMN_COLOR_STOCK_ID + " = ?";

    private Connection conn;
    //prepared statements for products
    PreparedStatement checkProductExists;
    PreparedStatement checkColorExists;
    PreparedStatement checkSizeExists;
    PreparedStatement insertProductChainPrep;
    PreparedStatement insertColorPrep;
    PreparedStatement insertSizePrep;
    PreparedStatement insertStock;
    public boolean open() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            checkProductExists = conn.prepareStatement(CHECK_IF_PRODUCT_EXISTS);
            insertProductChainPrep = conn.prepareStatement(INSERT_PRODUCTS,Statement.RETURN_GENERATED_KEYS);
            checkColorExists = conn.prepareStatement(CHECK_IF_COLOR_EXISTS,Statement.RETURN_GENERATED_KEYS);
            insertColorPrep = conn.prepareStatement(INSERT_COLOR,Statement.RETURN_GENERATED_KEYS);
            checkSizeExists = conn.prepareStatement(CHECK_IF_SIZE_EXISTS,Statement.RETURN_GENERATED_KEYS);
            insertSizePrep = conn.prepareStatement(INSERT_SIZE,Statement.RETURN_GENERATED_KEYS);
            insertStock = conn.prepareStatement(INSERT_STOCK,Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if(checkProductExists != null){
                checkProductExists.close();
            }
            if(insertProductChainPrep != null){
                insertProductChainPrep.close();
            }
            if(checkColorExists != null){
                checkColorExists.close();
            }
            if(insertColorPrep != null){
                insertColorPrep.close();
            }
            if(checkSizeExists != null){
                checkSizeExists.close();
            }
            if(insertSizePrep != null){
                insertSizePrep.close();
            }
            if(insertStock != null){
                insertStock.close();
            }
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
        String sql = "SELECT COUNT(*) AS count FROM " + table;
        try(Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)){
            int count = 0;
            while(results.next()){
                count = results.getInt(1);
            }
            close();
            return count;
        }catch (SQLDataException e){
//            System.out.println("error: "+e.getMessage());
            close();
            return -1;
        }
    }

    public List<StockList> queryStockListView(String name) throws SQLException, ClassNotFoundException {
        open();
        // StringBuilder sb = StringBuilder("SELECT * FROM " + VIEW_STOCK_LIST);

        try {
            PreparedStatement statement = conn.prepareStatement(QUERY_VIEW_STOCK_LIST);
            statement.setString(1,name);
            ResultSet results = statement.executeQuery("SELECT * FROM " + VIEW_STOCK_LIST);
            List<StockList> stockList = new ArrayList<>();
            while (results.next()) {
                StockList stock = new StockList();
                stock.setProduct_name(results.getString(COLUMN_STOCK_LIST_PRODUCT_NAME));
                stock.setProduct_description(results.getString(COLUMN_STOCK_LIST_PRODUCT_DESCRIPTION));
                stock.setQuantity(results.getInt(COLUMN_STOCK_LIST_QUANTITY));
                stock.setBuying_price(results.getDouble(COLUMN_STOCK_LIST_BUYING_PRICE));
                stock.setColor_name(results.getString(COLUMN_STOCK_LIST_COLOR_NAME));
                stock.setColor_quantity(results.getInt(COLUMN_STOCK_LIST_COLOR_QUANTITY));
                stock.setSize_name(results.getString(COLUMN_STOCK_LIST_SIZE_NAME));
                stock.setSize_quantity(results.getInt(COLUMN_STOCK_LIST_SIZE_QUANTITY));
//                todo add date
                stockList.add(stock);
            }
            results.close();
            statement.close();
            close();
            return stockList;
        } catch (SQLException e) {
                close();
                return null;
        }
    }

    public int insertProduct(String name, String description) throws SQLException, ClassNotFoundException{
        open();

        try {
            PreparedStatement check_product_exists = conn.prepareStatement(CHECK_IF_PRODUCT_EXISTS);
            check_product_exists.setString(1,name);
            ResultSet results = check_product_exists.executeQuery();
            if(results.next()){
                return results.getInt(1);
            }else{
            PreparedStatement insert_products = conn.prepareStatement(INSERT_PRODUCTS,Statement.RETURN_GENERATED_KEYS);
            insert_products.setString(1,name);
            insert_products.setString(2,description);
            int affectedRows = insert_products.executeUpdate();
            if(affectedRows != 1){
                throw new SQLException("Could not insert product");
            }
            ResultSet generatedKeys = insert_products.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            }else{
                throw new SQLException("couldn't get id");
            }
          }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            close();
        }finally {
            close();
        }
        return -1;
    }

    private int insertProductsChain(Product product)throws  SQLException{
        checkProductExists.setString(1, product.getName());
        ResultSet results = checkProductExists.executeQuery();
        if(results.next()){
            return results.getInt(1);
        }else{
            insertProductChainPrep.setString(1,product.getName());
            insertProductChainPrep.setString(2,product.getDescription());
            int affectedRows = insertProductChainPrep.executeUpdate();
            if(affectedRows!=1){
                throw new SQLException("Couldn't insert product");
            }

            ResultSet generatedKeys = insertProductChainPrep.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            }else {
                throw new SQLException("couldn't get id for new product");
            }
        }
    }
    private int insertColorsChain(Color color)throws  SQLException{
        checkColorExists.setString(1, color.getName());
        checkColorExists.setInt(2,color.getStock_id());
        ResultSet results = checkColorExists.executeQuery();
        if(results.next()){
            return results.getInt(1);
        }else{
            insertColorPrep.setInt(1,color.getStock_id());
            insertColorPrep.setString(2,color.getName());
            insertColorPrep.setInt(3,color.getQuantity());
            int affectedRows = insertColorPrep.executeUpdate();
            if(affectedRows!=1){
                throw new SQLException("Couldn't insert product");
            }

            ResultSet generatedKeys = insertColorPrep.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            }else {
                throw new SQLException("couldn't get id for new product");
            }
        }
    }
    private int insertSizeChain(Size size)throws  SQLException{
        checkSizeExists.setString(1, size.getName());
        checkSizeExists.setInt(2,size.getStock_id());
        ResultSet results = checkSizeExists.executeQuery();
        if(results.next()){
            return results.getInt(1);
        }else{
            insertSizePrep.setInt(1,size.getStock_id());
            insertSizePrep.setString(2,size.getName());
            insertSizePrep.setInt(3,size.getQuantity());
            int affectedRows = insertSizePrep.executeUpdate();
            if(affectedRows!=1){
                throw new SQLException("Couldn't insert product");
            }

            ResultSet generatedKeys = insertSizePrep.getGeneratedKeys();
            if(generatedKeys.next()){
                return generatedKeys.getInt(1);
            }else {
                throw new SQLException("couldn't get id for new product");
            }
        }
    }

    public void newStock(Color color, Size size, Stock stock, Product product) throws ClassNotFoundException , SQLException{
        open();
        try{
            conn.setAutoCommit(false);
            int productId = insertProductsChain(product);
            insertStock.setInt(1,productId);
            insertStock.setInt(2,stock.getQuantity());
            insertStock.setDouble(3,stock.getBuying_price());
            System.out.println(insertStock.toString());
            int affectedRows = insertStock.executeUpdate();
            ResultSet generatedKeys = insertStock.getGeneratedKeys();
            if(generatedKeys.next() && affectedRows == 1) {
                int stock_id = generatedKeys.getInt(1);
                color.setStock_id(stock_id);
                size.setStock_id(stock_id);
                insertSizeChain(size);
                insertColorsChain(color);
                conn.commit();
            }else{
                throw new SQLException("The stock insert failed");
            }
        }catch (Exception e){
            System.out.println("Insert stock error: "+ e.getMessage());
            try{
                System.out.println("Performing rollback");
                conn.rollback();
            }catch (SQLException ee){
                System.out.println("error! rollback failed: "+ e.getMessage());
            }
        }finally {
            try{
                System.out.println("Setting default commit behaviour");
                conn.setAutoCommit(true);
                close();
            }catch (SQLException ee){
                System.out.println("Error: "+ ee.getMessage());
            }
        }
    }

    public boolean UpdateProducts(Product product) throws  SQLException,ClassNotFoundException{
        open();
        try{
            PreparedStatement update_products = conn.prepareStatement(UPDATE_PRODUCT);
            update_products.setString(1,product.getName());
            update_products.setString(2, product.getDescription());
            update_products.setInt(3,product.getId());
            System.out.println(update_products.toString());
            int affectedRows = update_products.executeUpdate();
            close();
            return affectedRows == 1;
        }catch (SQLException e){
            System.out.println("error: "+ e.getMessage());
            close();
            return false;
        }

    }

    public List<Colors> queryColors(Color color){
        try{
            open();
            PreparedStatement queryColors = conn.prepareStatement(QUERY_COLORS);
            queryColors.setInt(1, color.getStock_id());
            ResultSet results = queryColors.executeQuery();
            List<Color> colors = new ArrayList();
            while(results.next()){
                Color color = new Color();
                color.setId(results.getInt(COLUMN_COLOR_ID));
                color.setName(results.getString(COLUMN_COLOR_NAME));
                color.setQuantity(results.getInt(COLUMN_COLOR_QUANTITY));
                color.setStock_id(results.getInt(COLUMN_COLOR_STOCK_ID));
                colors.add(color)
            }
            close();
            return colors;
        }catch(Exeception e){
            close();
            return null;
        }
    }
}
