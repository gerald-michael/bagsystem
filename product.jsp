<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayListjavax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
<div class="col s12 m4"> 
    <div class="card-panel">
        <h4 class="header2">New Product</h4>
        <form action="create" method="post" enctype = "multipart/form-data">
            <div class="input-field">
                <input type="text" name="name" id="ProductName">
                <label for="ProductName"> Product Name </label>
            </div>
            <div class="input-field">
                <textarea id="description" name="description" class="materialize-textarea" length="120"></textarea>
                <label for="description"> description</label>
            </div>
            <div class="input-field file-field">
                <div class="btn blue">
                    <i class="material-icons">attach_file</i>
                    <input type="file" name="file">
                </div>
                <div class="file-path-wrapper">
                    <input type="text" class="file-path validate" placeholder="Product image">
                </div>
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
        <h4 class="header2">Products</h4>
        <ul class="collection">
            <%
                ProductDao productDao = new ProductDao();
                ServletContext context = getServletContext();
                String filepath = context.getInitParameter("product-upload");
                List<Product> products =productDao.queryProducts(ProductDao.ORDER_BY_NONE);
                for(Product product:products){
                    out.println("<li class='collection-item avatar'>");
                    out.println("<img src='../portal/products/"+product.getImageUri()+"' alt='yuna' class='circle'>");
                    out.println("<span class='title'>"+ product.getName() +"</span>");
                    out.println("<p>"+product.getDescription()+"</p>");
                    out.println("<div class='secondary-content'>");
                    out.println("<a href='#modal1' class='modal-trigger'><i class='material-icons green-text'>edit</i></a>");
                    out.println("<a href='#!'><i class='material-icons red-text'>delete</i></a>");
                    out.println("</div>");
                    out.println("</li>");
                }
            %>
            </ul>
        </div>
    </div>
</div>
<div id="modal1" class="modal">
    <form method="post" action="updateUser">
        <div class="modal-content">
        <h4>Update user</h4>
            <div class="input-field">
                <label for="first">First Name</label><input type="text" name="firstName" id="first">
            </div>
            <div class="input-field">
                <label for="last">Last Name</label><input type="text" name="lastName" id="last">
            </div>
            <div class="input-field">
                <label for="password">Password</label><input type="password" name="password" id="password">
            </div>
            <div class="input-field">
                <label for="confirm">Confirm Password</label><input type="password" name="confirm" id="confirm">
            </div>
        </div>
        <div class="modal-footer">
            <button class="btn waves-effect waves-light blue" type="submit" name="action">update
                <i class="material-icons right">create</i>
            </button>
        </div>
    </form>
</div>
<%@ include file="footer.jsp" %>
