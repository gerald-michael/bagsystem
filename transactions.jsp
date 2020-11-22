<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.TransactionList,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
  <div class="row">
    <div class="col s12 m4"> 
        <div class="card-panel">
            <h4 class="header2">New Transaction</h4>
            <form action="createstock" method="post">
                <div class="input-field">
                    <input type="text" name="stock" id="stock">
                    <label for="stock"> Stock </label>
                </div>
                <div class="input-field">
                    <input type="number" name="quantity" id="quantity">
                    <label for="quantity"> Quantity</label>
                </div>
                <div class="input-field">
                    <input type="text" name="buyingprice" id="buyingprice">
                    <label for="buyingprice"> Selling Price</label>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">
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
                        <th>Last Updated By</th>
                        <th>Date Last Updated</th>
                        <th>Date Created</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ProductDao productDao = new ProductDao();
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
                            out.println("<td>"+transaction.getUpdated_by() +"</td>");
                            out.println("<td>"+transaction.getDate_updated()+ "</td>");
                            out.println("<td>"+transaction.getDate_created()+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
  </div>
  <%-- <c:out value="${productDao}"/> --%>
  <%-- <c:forEach items="stockList" var="stock">
    ${stock}
  </c:forEach> --%>

<%@ include file="footer.jsp" %>
