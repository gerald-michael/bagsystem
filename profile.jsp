<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ page import="com.products.dao.ProductDao,com.products.bean.Product,java.util.List,java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="container">
    <div id="profile-card" class="card">
        <div class="card-image waves-effect waves-block waves-light">
            <img class="activator" src="../portal/images/office.jpg" alt="user bg">
        </div>
        <div class="card-content">
            <img src="../portal/images/yuna.jpg" alt="" class="circle responsive-img activator card-profile-image cyan lighten-1 padding-2">
            <a class="btn-floating btn-move-up waves-effect waves-light modal-trigger orange z-depth-4 right" href="#modal1">
            <i class="material-icons">edit</i>
            </a>
            <span class="card-title activator grey-text text-darken-4">${username}</span>
            <p>
            <i class="material-icons">perm_identity</i> Project Manager</p>
            <p>
            <i class="material-icons">perm_phone_msg</i> +1 (612) 222 8989</p>
            <p>
            <i class="material-icons">email</i> yourmail@domain.com</p>
        </div>
        <div class="card-reveal">
            <span class="card-title grey-text text-darken-4">Roger Waters
            <i class="material-icons right">close</i>
            </span>
            <p>Here is some more information about this card.</p>
            <p>
            <i class="material-icons">perm_identity</i> Project Manager</p>
            <p>
            <i class="material-icons">perm_phone_msg</i> +1 (612) 222 8989</p>
            <p>
            <i class="material-icons">email</i> yourmail@domain.com</p>
            <p>
            <i class="material-icons">cake</i> 18th June 1990
            </p>
            <p>
            </p>
            <p>
            <i class="material-icons">airplanemode_active</i> BAR - AUS
            </p>
            <p>
            </p>
        </div>
    </div>
    <div id="modal1" class="modal">
        <form method="post" action="updateUser" enctype = "multipart/form-data">
            <div class="modal-content">
            <h4>Update user</h4>
                <div class="input-field file-field">
                    <div class="btn blue">
                        <i class="material-icons">attach_file</i>
                        <input type="file" name="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input type="text" class="file-path validate" placeholder="Profile image">
                    </div>
                </div>
                <div class="input-field">
                    <label for="first">First Name</label><input type="text" name="firstName" id="first">
                </div>
                <div class="input-field">
                    <label for="last">Last Name</label><input type="text" name="lastName" id="last">
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn waves-effect waves-light blue" type="submit" name="action">update
                    <i class="material-icons right">create</i>
                </button>
            </div>
        </form>
    </div>
</div>
</div>
<%@ include file="footer.jsp" %>
