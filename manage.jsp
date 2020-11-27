<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
    <div class="col s12">
        <ul class="tabs">
            <li class="tab col s3"><a href="#user" class="active">Users</a></li>
            <li class="tab col s3"><a href="#groups">Groups</a></li>
            <li class="tab col s3"><a href="#permissions">Permissions</a></li>
        </ul>
    </div>
    <div id="user" class="col s12">
        <%@ include file="users.jsp"%>
    </div>
    <div id="groups" class="col s12">
        <%@ include file="groups.jsp"%>
    </div>
    <div id="permissions" class="col s12">
        <%@ include file="permissions.jsp"%>
    </div>
</div>
<%@ include file="footer.jsp" %>
