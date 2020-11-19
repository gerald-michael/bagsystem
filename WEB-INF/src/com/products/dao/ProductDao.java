package com.products.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.authentication.bean.User;
import com.authentication.dao.AuthenticationDao;
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

    // stocklist view
    public static final String VIEW_STOCK_LIST = "stock_list";
    public static final String COLUMN_STOCK_LIST_PRODUCT_NAME = "product_name";
    public static final String COLUMN_STOCK_LIST_PRODUCT_DESCRIPTION = "product_description";
    public static final String COLUMN_STOCK_LIST_QUANTITY = "quantity";
    public static final String COLUMN_STOCK_LIST_BUYING_PRICE = "buying_price";
    public static final String COLUMN_STOCK_LIST_COLOR_NAME = "color";
    public static final String COLUMN_STOCK_LIST_SIZE_NAME = "size";
    public static final String COLUMN_STOCK_LIST_UPDATED = "date_updated";
    public static final String COLUMN_STOCK_LIST_DATE_CREATED = "date_created";

    // transaction list view
    public static final String VIEW_TRANSACTION_LIST = "transaction_list";
    public static final String COLUMN_TRANSACTION_LIST_PRODUCT_NAME = "product_name";
    public static final String COLUMN_TRANSACTION_LIST_QUANTITY = "quantity";
    public static final String COLUMN_TRANSACTION_LIST_BUYING_PRICE = "buying_price";
    public static final String COLUMN_TRANSACTION_LIST_SELLING_PRICE = "selling_price";
    public static final String COLUMN_TRANSACTION_LIST_COLOR_NAME = "color";
    public static final String COLUMN_TRANSACTION_LIST_SIZE_NAME = "size";
    public static final String COLUMN_TRANSACTION_LIST_ADDED_BY = "added_by";
    public static final String COLUMN_TRANSACTION_LIST_UPDATED_BY = "updated_by";
    public static final String COLUMN_TRANSACTION_LIST_UPDATED = "date_updated";
    public static final String COLUMN_TRANSACTION_LIST_DATE_CREATED = "date_created";
    // sort order

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // queries
    public static final String CHECK_IF_PRODUCT_EXISTS = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCT_NAME + " = ?";
    public static final String QUERY_VIEW_STOCK_LIST = "SELECT * FROM " + VIEW_STOCK_LIST ;
    public static final String INSERT_PRODUCTS = "INSERT INTO " + TABLE_PRODUCTS + " (" + COLUMN_PRODUCT_NAME + ", "+ COLUMN_PRODUCT_DESCRIPTION + ", "+ COLUMN_PRODUCT_ADDED_BY+ ", " + COLUMN_PRODUCT_IMAGE+") VALUES (?,?,?,?)";
    public static final String INSERT_STOCK = "INSERT INTO " + TABLE_STOCK + " ("+ COLUMN_STOCK_PRODUCT_ID + ", " + COLUMN_STOCK_QUANTITY + ", " + COLUMN_STOCK_BUYING_PRICE + ", " + COLUMN_STOCK_COLOR+", " +COLUMN_STOCK_SIZE + ", "+COLUMN_STOCK_ADDED_BY+ ") VALUES (?,?,?,?,?,?)";
    public static final String INSERT_TRANSACTION = "INSERT INTO " + TABLE_TRANSACTIONS + " (" + COLUMN_TRANSACTION_STOCK_ID + ", "  + COLUMN_TRANSACTION_QUANTITY + ", " + COLUMN_TRANSACTION_BUYING_PRICE + ", " + COLUMN_TRANSACTION_SELLING_PRICE + ", "+ COLUMN_TRANSACTION_ADDED_BY + ") VALUES (?,?,?,?,?)";
    public static final String UPDATE_PRODUCT = "UPDATE " + TABLE_PRODUCTS + " SET " + COLUMN_PRODUCT_NAME + " = ? ," + COLUMN_PRODUCT_DESCRIPTION + "=? ,"+ COLUMN_PRODUCT_UPDATED_BY + "=? ,"+COLUMN_PRODUCT_IMAGE +  " = ? WHERE " + COLUMN_PRODUCT_ID + " = ?";
    public static final String GET_STOCK = "SELECT * FROM "+ TABLE_STOCK + " WHERE " + COLUMN_STOCK_ID + " =?";
    public static final String UPDATE_STOCK_QUANTITY = "UPDATE " + TABLE_STOCK + " SET " + COLUMN_STOCK_QUANTITY + " =? WHERE " + COLUMN_STOCK_ID + " =?";
    public static final String GET_TRANSACTIONS = "SELECT * FROM " + VIEW_TRANSACTION_LIST;
    private Connection conn;
    //prepared statements for products
    PreparedStatement checkProductExists;
    PreparedStatement insertProductChainPrep;
    PreparedStatement insertStock;
    PreparedStatement insertTransaction;
    PreparedStatement getStock;
    PreparedStatement updateStockQuantity;
    public boolean open() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            checkProductExists = conn.prepareStatement(CHECK_IF_PRODUCT_EXISTS);
            insertProductChainPrep = conn.prepareStatement(INSERT_PRODUCTS,Statement.RETURN_GENERATED_KEYS);
            insertStock = conn.prepareStatement(INSERT_STOCK,Statement.RETURN_GENERATED_KEYS);
            insertTransaction = conn.prepareStatement(INSERT_TRANSACTION);
            getStock = conn.prepareStatement(GET_STOCK);
            updateStockQuantity = conn.prepareStatement(UPDATE_STOCK_QUANTITY);
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
            if(insertStock != null){
                insertStock.close();
            }
            if(insertTransaction != null){
                insertTransaction.close();
            }
            if(getStock != null){
                getStock.close();
            }
            if(updateStockQuantity != null){
                updateStockQuantity.close();
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
            if (sortOrder == ORDER_BY_DESC) {
                sb.append(" DESC");
            } else {
                sb.append(" ASC");
            }
        }
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sb.toString())) {
            List<Product> products = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (results.next()) {
                Product product = new Product();
                product.setId(results.getInt(COLUMN_PRODUCT_ID));
                product.setName(results.getString(COLUMN_PRODUCT_NAME));
                product.setDescription(results.getString(COLUMN_PRODUCT_DESCRIPTION));
                product.setImageUri(results.getString(COLUMN_PRODUCT_IMAGE));
                product.setUpdated_by(results.getInt(COLUMN_PRODUCT_UPDATED_BY));
                product.setAdded_by(results.getInt(COLUMN_PRODUCT_ADDED_BY));
                product.setDate_created(format.parse(results.getString(COLUMN_PRODUCT_DATE_CREATED)));
                if(results.getString(COLUMN_PRODUCT_DATE_UPDATED) != null){
                    product.setDate_updated(format.parse(results.getString(COLUMN_PRODUCT_DATE_UPDATED)));
                }
                products.add(product);
            }
            close();
            return products;
        } catch (Exception e) {
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
            close();
            return -1;
        }
    }

    public List<StockList> getStock() throws SQLException, ClassNotFoundException {
        open();
        try {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(QUERY_VIEW_STOCK_LIST);
            List<StockList> stockList = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (results.next()) {
                StockList stock = new StockList();
                stock.setProduct_name(results.getString(COLUMN_STOCK_LIST_PRODUCT_NAME));
                stock.setProduct_description(results.getString(COLUMN_STOCK_LIST_PRODUCT_DESCRIPTION));
                stock.setQuantity(results.getInt(COLUMN_STOCK_LIST_QUANTITY));
                stock.setBuying_price(results.getDouble(COLUMN_STOCK_LIST_BUYING_PRICE));
                stock.setColor_name(results.getString(COLUMN_STOCK_LIST_COLOR_NAME));
                stock.setSize_name(results.getString(COLUMN_STOCK_LIST_SIZE_NAME));
                stock.setDate_created(format.parse(results.getString(COLUMN_STOCK_LIST_DATE_CREATED)));
                if(results.getString(COLUMN_STOCK_LIST_UPDATED) != null){
                    stock.setDate_updated(format.parse(results.getString(COLUMN_STOCK_LIST_UPDATED)));
                }                stockList.add(stock);
            }
            results.close();
            statement.close();
            close();
            return stockList;
        } catch (Exception e) {
            System.out.println("error: "+e.getMessage());
            close();
            return null;
        }
    }

    public int createProduct(Product product) throws SQLException, ClassNotFoundException{
        open();

        try {
            PreparedStatement check_product_exists = conn.prepareStatement(CHECK_IF_PRODUCT_EXISTS);
            check_product_exists.setString(1,product.getName());
            ResultSet results = check_product_exists.executeQuery();
            if(results.next()){
                return results.getInt(1);
            }else{
                PreparedStatement insert_products = conn.prepareStatement(INSERT_PRODUCTS,Statement.RETURN_GENERATED_KEYS);
                insert_products.setString(1,product.getName());
                insert_products.setString(2,product.getDescription());
                insert_products.setInt(3, product.getAdded_by());
                insert_products.setString(4, product.getImageUri());
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
            insertProductChainPrep.setInt(3, product.getAdded_by());
            insertProductChainPrep.setString(4, product.getImageUri());
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

    public void createStock(Stock stock, Product product) throws ClassNotFoundException , SQLException{
        open();
        try{
            conn.setAutoCommit(false);
            int productId = insertProductsChain(product);
            insertStock.setInt(1,productId);
            insertStock.setInt(2,stock.getQuantity());
            insertStock.setDouble(3,stock.getBuying_price());
            insertStock.setString(4,stock.getColor());
            insertStock.setString(5,stock.getSize());
            insertStock.setInt(6,stock.getAdded_by());
            System.out.println(insertStock.toString());
            int affectedRows = insertStock.executeUpdate();
            ResultSet generatedKeys = insertStock.getGeneratedKeys();
            if(generatedKeys.next() && affectedRows == 1) {
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
            update_products.setInt(3,product.getUpdated_by());
            update_products.setString(4, product.getImageUri());
            update_products.setInt(5,product.getId());
            System.out.println(update_products.toString());
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
    private Stock validateStockIsExistsAndIsEnough(Stock stock) throws SQLException{
        getStock.setInt(1,stock.getId());
        ResultSet results = getStock.executeQuery();
        if(results.next()){
            Stock current = new Stock();
            current.setId(results.getInt(COLUMN_STOCK_ID));
            current.setBuying_price(results.getDouble(COLUMN_STOCK_BUYING_PRICE));
            current.setQuantity(results.getInt(COLUMN_STOCK_QUANTITY));
            if(current.getQuantity() >= stock.getQuantity()){
                stock.setBuying_price(current.getBuying_price());
                stock.setQuantity(current.getQuantity()-stock.getQuantity());
                return stock;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    private boolean UpdateStockChain(Stock stock) throws SQLException,ClassNotFoundException{
        updateStockQuantity.setInt(1,stock.getQuantity());
        updateStockQuantity.setInt(2,stock.getId());
        int affectedRows = updateStockQuantity.executeUpdate();
        if(affectedRows==1){
            return true;
        }else {
            throw new SQLException("failed to update stock");
        }
    }
    public void createTransaction(Transaction transaction){
        try{
            open();
            conn.setAutoCommit(false);
            Stock stock = new Stock();
            stock.setId(transaction.getStock_id());
            stock.setQuantity(transaction.getQuantity());
            stock.setBuying_price(transaction.getBuying_price());
            stock = validateStockIsExistsAndIsEnough(stock);
            if (stock != null){
                boolean stockIsUpdated = UpdateStockChain(stock);
                transaction.setBuying_price(stock.getBuying_price());
                insertTransaction.setInt(1,transaction.getStock_id());
                insertTransaction.setInt(2,transaction.getQuantity());
                insertTransaction.setDouble(3,transaction.getBuying_price());
                insertTransaction.setDouble(4,transaction.getSelling_price());
                insertTransaction.setInt(5,transaction.getAdded_by());
                System.out.println(insertTransaction.toString());
                int affectedRows = insertTransaction.executeUpdate();
                if(affectedRows == 1 && stockIsUpdated){
                    conn.commit();
                }else {
                    throw new SQLException("failed to create transaction");
                }
            }else{
                throw new SQLException("not enough stock");
            }
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
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

    public List<TransactionList> getTransactions(){
        try{
            open();
            PreparedStatement statement = conn.prepareStatement(GET_TRANSACTIONS);
            ResultSet results = statement.executeQuery();
            List<TransactionList> transactions = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            User user = new User();
            AuthenticationDao authenticationDao = new AuthenticationDao();
            while (results.next()){
                TransactionList transaction = new TransactionList();
                transaction.setProduct_name(results.getString(COLUMN_TRANSACTION_LIST_PRODUCT_NAME));
                transaction.setQuantity(results.getInt(COLUMN_TRANSACTION_LIST_QUANTITY));
                transaction.setBuying_price(results.getDouble(COLUMN_TRANSACTION_LIST_BUYING_PRICE));
                transaction.setSelling_price(results.getDouble(COLUMN_TRANSACTION_LIST_SELLING_PRICE));
                transaction.setColor_name(results.getString(COLUMN_TRANSACTION_LIST_COLOR_NAME));
                transaction.setSize_name(results.getString(COLUMN_TRANSACTION_LIST_SIZE_NAME));
                transaction.setDate_created(format.parse(results.getString(COLUMN_TRANSACTION_LIST_DATE_CREATED)));
                if(results.getString(COLUMN_TRANSACTION_LIST_UPDATED) != null){
                    transaction.setDate_updated(format.parse(results.getString(COLUMN_TRANSACTION_LIST_UPDATED)));
                }
                user.setId(results.getInt(COLUMN_TRANSACTION_LIST_ADDED_BY));
                user = authenticationDao.getUserWithId(user);
                transaction.setAdded_by(user.getUsername());
                if(results.getInt(COLUMN_TRANSACTION_LIST_UPDATED_BY) != 0){
                    user.setId(results.getInt(COLUMN_TRANSACTION_LIST_ADDED_BY));
                    user = authenticationDao.getUserWithId(user);
                    transaction.setUpdated_by(user.getUsername());
                }else{
                    transaction.setUpdated_by("never");
                }
                transactions.add(transaction);
            }
            close();
            return transactions;
        }catch (Exception e){
            System.out.println("error: "+ e.getMessage());
            close();
            return null;
        }
    }
}
