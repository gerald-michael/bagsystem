<%@ page language="java" contentType="text/html" errorPage="error.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      type="text/css"
      rel="stylesheet"
      href="css/materialize.css"
      media="screen,projection"
    />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />

    <title>BagSystem</title>
  </head>
  <body>
    <h1>Login</h1>
    <form action="login" method="post">
      <label for="username">Username</label><input type="text" name="username" id="username">
      <label for="password">Password</label><input type="password" name="password" id="password">
      <input type="submit" value="login">
    </form>

    <%
      if(request.getParameter("message")!= null){
        out.println(request.getParameter("message"));
      }
    %>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
