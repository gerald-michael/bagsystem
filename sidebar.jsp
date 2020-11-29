  <%@ page import="com.authentication.dao.*,com.authentication.bean.*,java.util.List,java.util.ArrayList"%>
  <ul id="slide-out" class="sidenav sidenav-fixed">
    <li><div class="user-view">
      <div class="background">
        <img class='materialboxed materialboxed responsive-img' src="../portal/images/office.jpg">
      </div>
      <%
        out.println("<a href='profile.jsp'><img class='circle' src='../portal/profile/"+userx.getProfileImage()+"'></a>");
      %>
      <a href="profile.jsp"><span class="white-text name">${username}</span></a>
    </div></li>
    <li><a href="index.jsp"><i class="material-icons">dashboard</i>Dashbord</a></li> 
    <%
        UserGroupPermission userGroupPermissionn = new UserGroupPermission();
        userGroupPermissionn.setUsername(userx.getUsername());
        List<UserGroupPermission> userGroupPermissionss = authenticationDao.queryUserGroupPermissionView(userGroupPermissionn);
        List<String> permissions = new ArrayList<>();
        for (UserGroupPermission user_group_permissions : userGroupPermissionss) {
          permissions.add(user_group_permissions.getPermissionName());
        }
        if(permissions.contains("all") || permissions.contains("viewGroups") || permissions.contains("deleteGroups") || permissions.contains("viewPermissions") || permissions.contains("viewUsers") || permissions.contains("assignPermission") || permissions.contains("assignGroups")){
          out.println("<li><a href='manage.jsp'><i class='material-icons'>account_box</i>Manage</a></li>");
        }
        if(permissions.contains("all")||permissions.contains("addProducts")||permissions.contains("updateProducts")||permissions.contains("viewProducts")||permissions.contains("deleteProducts")){
          out.println("<li><a href='product.jsp'>Products</a></li>");
        }
        if(permissions.contains("all") || permissions.contains("addStock") || permissions.contains("updateStock") || permissions.contains("viewStock") || permissions.contains("deleteStock")){
          out.println("<li><a href='stock.jsp'>Stock</a></li>");
        }
        if(permissions.contains("all")||permissions.contains("addTransaction")||permissions.contains("updateTransaction")||permissions.contains("viewTransaction")||permissions.contains("deleteTransaction")){
          out.println("<li><a href='transactions.jsp'>Transaction</a></li>");
        }
    %>   
    <li><a href="statistics.jsp"><i class="material-icons">graphic_eq</i>statistics</a></li>
    <li><a href="reports.jsp"><i class="material-icons">report</i>reports</a></li>
  </ul>
        