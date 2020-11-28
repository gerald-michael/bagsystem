<%@ page import="com.authentication.dao.*,com.authentication.bean.*,java.util.List,java.util.ArrayList"%>
<div class="row">
    <div class="col s12 m6"> 
        <div class="card-panel">
            <h4 class="header2">Create Group</h4>
            <form action="create-group" method="post">
                <div class="input-field">
                    <input type="text" name="name" id="ProductName">
                    <label for="ProductName"> Group Name </label>
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
            <h4 class="header2">Assign User to Group</h4>
            <form action="assignUser" method="post">
                <div class="input-field">
                    <select class="icons" name="groupId">
                         <option value="" disabled selected>Choose Group</option>
                        <%
                            List <Group> groups = authenticationDao.getGroups();
                            for(Group group: groups){
                                out.println("<option value="+group.getId()+" class='left'>"+group.getName()+"</option>");
                            }
                        %>
                    </select>
                    <label>Group</label>
                </div>
                <div class="input-field">
                    <select class="icons" name="userId">
                         <option value="" disabled selected>Choose User</option>
                        <%
                            List <User> userss = authenticationDao.getUsers();
                            for(User us: userss){
                                out.println("<option value="+us.getId()+" class='left'>"+us.getUsername()+"</option>");
                            }
                        %>
                    </select>
                    <label>User</label>
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
            <h4 class="header2">User Groups</h4>
            <table class="responsive-table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Group name</th>
                        <th>remove</th>
                    </tr>
                </thead>
                    <%
                        List<UserGroups> userGroups = new ArrayList<>();
                        userGroups = authenticationDao.getUserGroups();
                        for(UserGroups userGroup: userGroups){
                            out.println("<tr>");
                            out.println("<td>"+userGroup.getUsername()+ "</td>");
                            out.println("<td>"+userGroup.getGroupName()+ "</td>");
                            out.println("<td>"+"<a href='removeUser?groupId="+userGroup.getGroupId()+"&userId="+userGroup.getUserId()+"'><i class='material-icons orange-text'>remove_circle</i></form>"+"</td>");
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
            <h4 class="header2">All Groups</h4>
            <table class="responsive-table">
                <thead>
                    <tr>
                        <th>Group</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                    <%
                        List<Group> groupsss = new ArrayList<>();
                        groupsss = authenticationDao.getGroups();
                        for(Group group: groupsss){
                            out.println("<tr>");
                            out.println("<td>"+group.getName()+ "</td>");
                            out.println("<td>"+"<a href='deleteGroup?name="+group.getName()+"'><i class='material-icons red-text'>delete</i></a>"+ "</td>");
                            out.println("</tr>");
                        }
                    %>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>