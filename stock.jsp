<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.StockList,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%-- <%@ include file="sidebar.jsp"%> --%>
  <div class="row">
    <div class="col s12 m4"> 
        <div class="card-panel">
            <h4 class="header2">New Stock</h4>
            <form action="createstock" method="post">
                <div class="input-field">
                    <input type="text" name="product" id="product">
                    <label for="product"> Product </label>
                </div>
                <div class="input-field">
                    <input type="text" name="quantity" id="quantity">
                    <label for="quantity"> Quantity</label>
                </div>
                <div class="input-field">
                    <input type="text" name="buyingprice" id="buyingprice">
                    <label for="buyingprice"> Buying Price</label>
                </div>
                <div class="input-field">
                    <input type="text" name="color" id="color">
                    <label for="color"> Color</label>
                </div>
                <div class="input-field">
                    <input type="text" name="size" id="size">
                    <label for="size"> Size</label>
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
            <h4 class="header2">Available Stock</h4>
            <table class="responsive-table">
                <thead>
                    <tr>
                        <th>Product name</th>
                        <th>Color</th>
                        <th>Size</th>
                        <th>Quantity</th>
                        <th>Buying Price</th>
                        <th>Date Updated</th>
                        <th>Date Created</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ProductDao productDao = new ProductDao();
                        List<StockList> stockList = new ArrayList<>();
                        stockList = productDao.getStock();
                        for(StockList stock: stockList){
                            out.println("<tr>");
                            out.println("<td>"+stock.getProduct_name()+ "</td>");
                            out.println("<td>"+stock.getColor_name()+ "</td>");
                            out.println("<td>"+stock.getSize_name() +"</td>");
                            out.println("<td>"+stock.getQuantity()+ "</td>");
                            out.println("<td>"+stock.getBuying_price()+ "</td>");
                            out.println("<td>"+stock.getDate_updated()+ "</td>");
                            out.println("<td>"+stock.getDate_created()+ "</td>");
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