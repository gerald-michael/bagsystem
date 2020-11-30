<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
    <div class="col s12 m4"> 
        <div class="card-panel">
            <h4 class="header2">Users</h4>
            
        </div>
    </div>
    <div class="col s12 m8"> 
        <div class="card-panel">
            <h4 class="header2">Groups</h4>
        </div>
    </div>
</div>
<div class="row">
    <div class="col s12 m4"> 
        <div class="card-panel">
            <h4 class="header2">Users</h4>
            
        </div>
    </div>
    <div class="col s12 m8"> 
        <div class="card-panel">
            <h4 class="header2">Groups</h4>
        </div>
    </div>
</div>


<!-- Reports Benon -->
<div class="container">
    <div class="col s12 m8"> 
        <div class="card-panel">
            <h4 class="header2">General</h4>
        </div>
    </div>
    <div class="row">

        <div class="col s12 card">
            <div class="table">
                <table>
                    <tr>
                        
                        <select value="">
                            <option name="hour" id="">hour</option>
                            <option name="day" id="">day</option>
                            <option name="week" id="">week</option>
                            <option name="month" id="">Month</option>
                            <option name="year" id="">Year</option>
                        </select>
                    </tr>
                    <%-- <%
                      ReportsDao reportsDao = new ReportsDao();
                     %> --%>
                    <tr class="report">

                        <div class="hour">
                            <div class="col s12 m12">
                                <div class="padding-4">
                                    <div class="col s4 m4">
                                      <p>Previous</p>
                                    </div>
                                    <div class="col s5 m5 center-align">
                                        <p >Bags sold: </p>
                                        <p>Bags Stocked: </p>
                                        <p>Transactions Made: </p>
                                      </div>
                                    <div class="col s3 m3 right-align">
                                      <%out.println("<p>"+getCount(TABLE_TRANSACTIONS )+"</p>");%>
                                      <p>66</p>
                                      <%out.println("<p>"+getCount(TABLE_TRANSACTIONS )+"</p>");%>
                                    </div>
                                  </div>
                            </div>
                            <div class="col s12 m12">
                                <div class="padding-4">
                                    <div class="col s4 m4">
                                      <p>Now</p>
                                    </div>
                                    <div class="col s5 m5 center-align">
                                        <p >Bags sold: </p>
                                        <p>Bags Stocked: </p>
                                        <p>Transactions Made: </p>
                                      </div>
                                    <div class="col s3 m3 right-align">
                                      <p >55</p>
                                      <p>66</p>
                                      <p> 8</p>
                                    </div>
                                  </div>
                            </div>
                        </div>
                        
                        <div class="days">
                            <div class="day">
                                <div class="col s12 m12">
                                    <div class="padding-4">
                                        <div class="col s4 m4">
                                          <p>Day</p>
                                        </div>
                                        <div class="col s5 m5 center-align">
                                            <p >Bags sold: </p>
                                            <p>Bags Stocked: </p>
                                            <p>Transactions Made: </p>
                                          </div>
                                        <div class="col s3 m3 right-align">
                                          <p >55</p>
                                          <p>66</p>
                                          <p> 8</p>
                                        </div>
                                      </div>
                                </div>
                            </div>
                        </div>

                        <div class="months">
                            <div class="month">
                                <div class="col s12 m12">
                                    <div class="padding-4">
                                        <div class="col s4 m4">
                                          <p>Month</p>
                                        </div>
                                        <div class="col s5 m5 center-align">
                                            <p >Bags sold: </p>
                                            <p>Bags Stocked: </p>
                                            <p>Transactions Made: </p>
                                          </div>
                                        <div class="col s3 m3 right-align">
                                          <p >55</p>
                                          <p>66</p>
                                          <p> 8</p>
                                        </div>
                                      </div>
                                </div>
                            </div>                            
                        </div>

                        <div class="year">
                            <div class="col s12 m12">
                                <div class="padding-4">
                                    <div class="col s4 m4">
                                      <p>Year</p>
                                    </div>
                                    <div class="col s5 m5 center-align">
                                        <p >Bags sold: </p>
                                        <p>Bags Stocked: </p>
                                        <p>Transactions Made: </p>
                                      </div>
                                    <div class="col s3 m3 right-align">
                                      <p >55</p>
                                      <p>66</p>
                                      <p> 8</p>
                                    </div>
                                  </div>
                            </div>
                        </div>
                        
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- End of Benon -->
<%@ include file="footer.jsp" %>
