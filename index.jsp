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
    <%-- <nav><nav> --%>
    <div class="row"></div>
    
   <div class="row " style="padding-left:30%;padding-top:15%">
      <div class="col s12 m6">
      <div class="card blue-grey darken-1">
        <div class="card-content white-text">
          <span class="card-title">Login</span>

          <form action="login" method="post">
            <label for="username">Username</label><input type="text" name="username" id="username">
            <label for="password">Password</label><input type="password" name="password" id="password">

    
            <button class="btn waves-effect waves-light" type="submit" name="action">Submit
              <i class="material-icons right">login</i>
            </button>
          </form>

        </div>
      
    </div>
    <div>
  </div>

    <%
      if(request.getParameter("message")!= null){
        out.println(request.getParameter("message"));
      }
    %>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
