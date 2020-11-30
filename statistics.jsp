<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
 <link href="css/sb-admin-2.min.css" rel="stylesheet">
<%@ include file="sidebar.jsp"%>
  <div id="wrapper">

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Charts</h1>
          <p class="mb-4">Progressive charts of stock, sales and Transactions.</p>

          <!-- Content Row -->
          <div class="row">

            <div class="col-xl-8 col-lg-7">

              <!-- Area Chart -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Area Chart</h6>
                </div>
                <div class="card-body">
                  <div class="chart-area">
                    <canvas id="myAreaChart"></canvas>
                  </div>
                  <hr>
                 Stock per month
                </div>
              </div>

              <!-- Bar Chart -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Bar Chart</h6>
                </div>
                <div class="card-body">
                  <div class="chart-bar">
                    <canvas id="myBarChart"></canvas>
                  </div>
                  <hr>
                  Sales Per month
                </div>
              </div>

            </div>
            <script>
            let JAN =+"  <%
            JAN_STOCK
            %>";
              let FEB =+"  <%
            FEB_STOCK
            %>";
              let MAR =+"  <%
            MAR_STOCK
            %>";
            let APR =+"  <%
            APR_STOCK
            %>";
              let MAY =+"  <%
            MAY_STOCK
            %>";
            let JUN =+"  <%
            JUN_STOCK
            %>";
              let JUL =+"  <%
            JUL_STOCK
            %>";
              let AUG =+"  <%
            AUG_STOCK
            %>";
            let SEP =+"  <%
            SEP_STOCK
            %>";let OCT =+"  <%
            OCT_STOCK
            %>";let NOV =+"  <%
            NOV_STOCK
            %>";let DEC =+"  <%
            DEC_STOCK
            %>";
              let Jan =+"  <%
            JAN_PRODUCT
            %>";
              let Feb =+"  <%
            FEB_PRODUCT
            %>";
             let Mar =+"  <%
            MAR_PRODUCT
            %>";
            
             let Apr =+"  <%
            APR_PRODUCT
            %>";
            
             let May=+"  <%
            MAY_PRODUCT
            %>";
            
             let Jun =+"  <%
            JUN_PRODUCT
            %>";
            
            </script>
          

            <!-- Donut Chart -->
            <%-- <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">Donut Chart</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie pt-4">
                    <canvas id="myPieChart"></canvas>
                  </div>
                  <hr>
                  Transactions
                </div>
              </div>
            </div>
          </div> --%>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->


  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="js/vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>
  <script src="js/demo/chart-bar-demo.js"></script>


<%@ include file="footer.jsp" %>
