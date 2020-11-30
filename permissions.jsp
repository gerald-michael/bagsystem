<%@ page import="com.authentication.dao.*,com.authentication.bean.*,java.util.List,java.util.ArrayList"%>
<div class="row">
    <div class="col s12"> 
        <div class="card-panel">
            <h4 class="header2">Assign Permission to Group</h4>
            <form action="assignGroup" method="post">
                <div class="input-field">
                    <select class="icons" name="groupId">
                         <option value="" disabled selected>Choose Group</option>
                        <%
                            List <Group> groupss = authenticationDao.getGroups();
                            for(Group groupp: groupss){
                                out.println("<option value="+groupp.getId()+" class='left'>"+groupp.getName()+"</option>");
                            }
                        %>
                    </select>
                    <label>Group</label>
                </div>
                <div class="input-field">
                    <select class="icons" name="permissionId">
                         <option value="" disabled selected>Choose Permission</option>
                        <%
                            List <Permission> permissionsx = authenticationDao.getPermissions();
                            for(Permission permission:permissionsx){
                                out.println("<option value="+permission.getId()+" class='left'>"+permission.getName()+"</option>");
                            }
                        %>
                    </select>
                    <label>Permission</label>
                </div>
                <button class="btn waves-effect waves-light blue" type="submit" name="action">
                    Assign
                    <i class="material-icons right">add</i>
                </button>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="col s12 m6"> 
        <div class="card-panel">
            <h4 class="header2">All Permissions</h4>
            <table>
                <thead>
                    <tr>
                        <th>Permission Name</th>
                    </tr>
                </thead>
                    <%
                        List<Permission> permissionss = new ArrayList<>();
                        permissionss = authenticationDao.getPermissions();
                        for(Permission permission: permissionss){
                            out.println("<tr>");
                            out.println("<td>"+permission.getName()+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col s12 m6"> 
        <div class="card-panel">
            <div class="card-panel">
            <h4 class="header2">Group Permissions</h4>
            <table class="responsive-table">
                <thead>
                    <tr>
                        <th>Group Name</th>
                        <th>Permission Name</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<GroupPermissions> groupPermissions = new ArrayList<>();
                        groupPermissions = authenticationDao.getGroupsPermission();
                        for(GroupPermissions groupPermission:groupPermissions){
                            out.println("<tr>");
                            out.println("<td>"+groupPermission.getGroupName()+ "</td>");
                            out.println("<td>"+groupPermission.getPermissionName()+ "</td>");
                            out.println("<td>"+"<a href='removePermission?groupId="+groupPermission.getGroupId()+"&permissionId="+groupPermission.getPermissionId()+"'><i class='material-icons orange-text'>remove_circle</i></a>"+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>
        </div>
    </div> 
</div>