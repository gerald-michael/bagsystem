<%@ page language="java" contentType="text/html" errorPage="error.jsp"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="nav.jsp" %>
<%@ include file="sidebar.jsp"%>
<div class="container">
    <div class="row">
        <div id="profile-card" class="card">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="../portal/images/office.jpg" alt="user bg">
            </div>
            <div class="card-content">
                <%
                    out.println("<img src='../portal/profile/"+userx.getProfileImage()+"' alt='' class='circle responsive-img activator card-profile-image cyan lighten-1 padding-2'>");
                %>
                <a class="btn-floating btn-move-up waves-effect waves-light modal-trigger orange z-depth-4 right" href="#modal1">
                    <i class="material-icons">edit</i>
                </a>
                <span class="card-title activator grey-text text-darken-4">${username}</span>
                
            </div>
            <div class="card-reveal">
                <span class="card-title grey-text text-darken-4">${username}
                    <i class="material-icons right">close</i>
                </span>
                <p>User Info.</p>
                <%
                    out.println("<p>First Name: "+ userx.getFirstName() +"</p>");
                    out.println("<p>Last Name: "+ userx.getLastName() +"</p>");
                    out.println("<p>Date joined: " + userx.getDateCreated() +"</p>");
                %>
            </div>
        </div>
    </div>
    <div id="modal1" class="modal">
        <form method="post" action="updateUser" enctype="multipart/form-data">
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
<div class="row">
    <div class="col s12"> 
        <div class="card-panel">
            <h4 class="header2">User Groups And Permissions</h4>
            <table>
                <thead>
                    <tr>
                        <th>Group Name</th>
                        <th>Permission Name</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<UserGroupPermission> userGroupPermissions = new ArrayList<>();
                        UserGroupPermission userGroupPermission = new UserGroupPermission();
                        userGroupPermission.setUsername(userx.getUsername());
                        userGroupPermissions = authenticationDao.queryUserGroupPermissionView(userGroupPermission);
                        for(UserGroupPermission user_group_permission: userGroupPermissions){
                            out.println("<tr>");
                            out.println("<td>"+user_group_permission.getGroupName()+ "</td>");
                            out.println("<td>"+user_group_permission.getPermissionName()+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
