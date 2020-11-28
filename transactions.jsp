<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.*,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
  <div class="row">
    <div class="col s12 m4"> 
        <div class="card-panel">
            <h4 class="header2">New Transaction</h4>
            <form action="createtransaction" method="post">
                <div class="input-field">
                    <select class="icons" name="stock">
                         <option value="" disabled selected>Choose your Stock</option>
                        <%
                            ProductDao productDao = new ProductDao();
                            List<StockList> stockList = productDao.getStock();
                            for(StockList stock: stockList){
                                out.println("<option value="+stock.getStock_id()+" data-icon='../portal/products/"+stock.getImageUri()+"' class='left'>"+stock.getProduct_name() + " &nbsp;&nbsp; "+ stock.getQuantity() + " &nbsp;&nbsp; "+stock.getColor_name() + " &nbsp;&nbsp; "+stock.getSize_name()+"</option>");
                            }
                        %>
                    </select>
                    <label>Product</label>
                </div>
                <div class="input-field">
                    <input type="number" name="quantity" id="quantity">
                    <label for="quantity"> Quantity</label>
                </div>
                <div class="input-field">
                    <input type="text" name="sellingprice" id="sellingprice">
                    <label for="sellingprice"> Selling Price</label>
                </div>
                <button class="btn waves-effect waves-light blue" type="submit" name="action">
                    Create
                    <i class="material-icons right">add</i>
                </button>
            </form>
        </div>
    </div>
    <div class="col s12 m8"> 
        <div class="card-panel">
            <h4 class="header2">Transactions</h4>
            <table class="responsive-table">
                <thead>
                    <tr>
                        <th>Product name</th>
                        <th>Quantity</th>
                        <th>Buying Price</th>
                        <th>Selling Price</th>
                        <th>Color</th>
                        <th>Size</th>
                        <th>Added by</th>
                        <th>Date Created</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<TransactionList> transctionList = new ArrayList<>();
                        transctionList = productDao.getTransactions();
                        for(TransactionList transaction: transctionList){
                            out.println("<tr>");
                            out.println("<td>"+transaction.getProduct_name()+ "</td>");
                            out.println("<td>"+transaction.getQuantity()+ "</td>");
                            out.println("<td>"+transaction.getBuying_price()+ "</td>");
                            out.println("<td>"+transaction.getSelling_price()+ "</td>");
                            out.println("<td>"+transaction.getColor_name()+ "</td>");
                            out.println("<td>"+transaction.getSize_name() +"</td>");
                            out.println("<td>"+transaction.getAdded_by()+ "</td>");
                            out.println("<td>"+transaction.getDate_created()+ "</td>");
                            out.println("<td>"+"<a href='deleteTransaction?id="+ transaction.getTransactionId()+"'><i class='material-icons red-text'>delete</i></a>"+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
  </div>
<%@ include file="footer.jsp" %>
