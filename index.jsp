<%@ page language="java" contentType="text/html" errorPage="error.jsp"%>
<%@ page import="com.products.dao.ProductDao,com.products.bean.*,java.util.List,java.util.ArrayListjavax.servlet.*"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
  <div class="col s12 m6 l3 ">
      <div class="card gradient-45deg-light-blue-cyan gradient-shadow min-height-100 black-text">
          <div class="padding-4">
            <div class="col s7 m7">
              <i class="material-icons background-round mt-5"
                >account_circle</i
              >
              <p>Total Users</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                ProductDao productdao = new ProductDao();
                int totalUsers = productdao.getCount(AuthenticationDao.TABLE_USERS);
                out.println("<p>" +totalUsers+ "<p>");
              %>
            </div>
          </div>
        </div>
  </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-green-teal gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
            <i class="material-icons background-round mt-5"
              >group</i
            >
              <p>Total Groups</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                int totalGroups = productdao.getCount("`"+AuthenticationDao.TABLE_USERS+"`");
                out.println("<h5 class='mb-0'>" +totalGroups+ "</h5>");
              %>
            </div>
        </div>
      </div>
    </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-amber-amber gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
            <i class="material-icons background-round mt-5"
              >lock_open</i
            >
              <p>Total Permissions</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                int totalPermissions = productdao.getCount(AuthenticationDao.TABLE_PERMISSIONS);
                out.println("<h5 class='mb-0'>" +totalPermissions+ "</h5>");
              %>
            </div>
        </div>
      </div>
    </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-red-pink gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
            <i class="material-icons background-round mt-5"
              >attach_money</i
            >
              <p>Profit/loss</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                  List<TransactionList> transactions= productdao.getTransactions();
                  double profitOrLoss = 0;
                  int totalSales = 0;
                  for(TransactionList transaction: transactions){
                    profitOrLoss += (transaction.getSelling_price() - transaction.getBuying_price()) * transaction.getQuantity();
                    totalSales += transaction.getQuantity();
                  }
                  out.println("<h5 class='mb-0'>" +profitOrLoss+ "&nbsp;shs</h5>");
              %>
          </div>
      </div>
    </div>
</div>
<div class="row">
  <div class="col s12 m6 l3 ">
      <div class="card gradient-45deg-light-blue-cyan gradient-shadow min-height-100 black-text">
          <div class="padding-4">
            <div class="col s7 m7">
              <p>Total Products</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                int totalProducts = productdao.getCount(ProductDao.TABLE_PRODUCTS);
                out.println("<p>" +totalProducts+ "<p>");
              %>
            </div>
          </div>
        </div>
  </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-green-teal gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
              <p>Total Orders</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                int totalOrders = productdao.getCount(ProductDao.TABLE_TRANSACTIONS);
                out.println("<h5 class='mb-0'>" +totalOrders+ "</h5>");
              %>
            </div>
        </div>
      </div>
    </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-amber-amber gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
              <p>Total Sales</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                out.println("<h5 class='mb-0'>" +totalSales+ "</h5>");
              %>
            </div>
        </div>
      </div>
    </div>
    <div class="col s12 m6 l3">
      <div
        class="card gradient-45deg-red-pink gradient-shadow min-height-100 white-text"
      >
        <div class="padding-4">
          <div class="col s7 m7">
              <p>Total Stock</p>
            </div>
            <div class="col s5 m5 right-align">
              <p class="no-margin">&nbsp;</p>
              <%
                  List<StockList> stock= productdao.getStock();
                  int totalStock = 0;
                  for(StockList s: stock){
                    totalStock += s.getQuantity();
                  }
                  out.println("<h5 class='mb-0'>" +totalStock+ "</h5>");
              %>
          </div>
      </div>
    </div>
</div>
<%@ include file="footer.jsp" %>