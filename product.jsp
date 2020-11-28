<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayListjavax.servlet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="row">
    <div class="col s12 m6"> 
        <div class="card-panel">
            <h4 class="header2">New Product</h4>
            <form action="create" method="post" enctype = "multipart/form-data">
                <div class="input-field">
                    <input type="text" name="name" id="ProductName">
                    <label for="ProductName"> Product Name </label>
                </div>
                <div class="input-field">
                    <textarea id="description" name="description" class="materialize-textarea" length="120"></textarea>
                    <label for="description"> Description</label>
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
    <div class="col s12 m6"> 
        <div class="card-panel">
            <h4 class="header2">Update Product</h4>
            <form action="updateProduct" method="post" enctype = "multipart/form-data">
                
                <div class="input-field">
                    <select class="icons" name="name" id="product">
                         <option value="" disabled selected>Choose your product</option>
                        <%
                            ProductDao productDao = new ProductDao();
                            List <Product> productss = productDao.queryProducts(productDao.ORDER_BY_NONE);
                            for(Product product:productss){
                                out.println("<option value="+product.getName()+" data-icon='../portal/products/"+product.getImageUri()+"' class='left'>"+product.getName()+"</option>");
                            }
                        %>
                    </select>
                    <label for="product">Product</label>
                </div>
                <div class="input-field">
                    <textarea id="description" name="description" class="materialize-textarea" length="120"></textarea>
                    <label for="description">New Description</label>
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
                    Update
                    <i class="material-icons right">add</i>
                </button>
            </form>
        </div>
    </div>
    <div class="col s12"> 
        <div class="card-panel">
            <h4 class="header2">Products</h4>
            <ul class="collection">
                <%
                    ServletContext context = getServletContext();
                    String filepath = context.getInitParameter("product-upload");
                    List<Product> products =productDao.queryProducts(ProductDao.ORDER_BY_NONE);
                    for(Product product:products){
                        out.println("<li class='collection-item avatar'>");
                        out.println("<img src='../portal/products/"+product.getImageUri()+"' alt='yuna' class='circle'>");
                        out.println("<span class='title'>"+ product.getName() +"</span>");
                        out.println("<p>"+product.getDescription()+"</p>");
                        out.println("<div class='secondary-content'>");
                        out.println("<a href='deleteProduct?id="+product.getId()+"'><i class='material-icons red-text'>delete</i></a>");
                        out.println("</div>");
                        out.println("</li>");
                    }
                %>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
