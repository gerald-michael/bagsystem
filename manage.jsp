<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
<div class="col s12">
    <ul class="tabs">
        <li class="tab col s3"><a href="#user" class="active">Users</a></li>
        <li class="tab col s3"><a href="#groups">Permissions</a></li>
        <li class="tab col s3"><a href="#permissions">Groups</a></li>
    </ul>
</div>
    <div id="user" class="col s12">
        <div class="card-panel">
                <h4 class="header2">Users</h4>
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
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div id="groups" class="col s12">Permissions</div>
    <div id="permissions" class="col s12">Groups</div>
</div>
<%@ include file="footer.jsp" %>
