<%@ page import="com.authentication.dao.*,com.authentication.bean.*,java.util.List,java.util.ArrayListjavax.servlet.*"%>
<div class="card-panel">
    <h4 class="header2">Users</h4>
    <table class="responsive-table">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Date Last Updated Account</th>
                <th>Date Joined</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<User> users = new ArrayList<>();
                AuthenticationDao authenticationDao = new AuthenticationDao();
                users = authenticationDao.getUsers();
                for(User user: users){
                    out.println("<tr>");
                    out.println("<td>"+user.getFirstName()+ "</td>");
                    out.println("<td>"+user.getLastName()+ "</td>");
                    out.println("<td>"+user.getUsername() +"</td>");
                    if(user.getDateUpdated() == null){
                        out.println("<td>"+"never"+ "</td>");
                    }else{
                        out.println("<td>"+user.getDateUpdated()+ "</td>");
                    }
                    out.println("<td>"+user.getDateCreated()+ "</td>");
                    out.println("</tr>");
                }
            %>
        </tbody>
    </table>
</div>