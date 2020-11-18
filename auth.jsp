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
      <div class="container">
        <div class="card-panel">
            <h4 class="header2">Login</h4>
            <form action="login" method="post">
              <div class="input-field">
                <i class="material-icons prefix">account_circle</i>
                <label for="username">Username</label><input type="text" name="username" id="username">
              </div>
              <div class="input-field">
                <i class="material-icons prefix">lock_outline</i>
                <label for="password">Password</label><input type="password" name="password" id="password">
              </div>
              <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                <i class="material-icons right">login</i>
              </button>
            </form>
        </div>
        <div class="card-panel">
          <h4 class="header2">Sign Up</h4>
          <form action="signup" method="post">
            <div class="input-field">
              <label for="first">First Name</label><input type="text" name="firstName" id="first">
            </div>
            <div class="input-field">
              <label for="last">Last Name</label><input type="text" name="lastName" id="last">
            </div>
            <div class="input-field">
              <label for="username">Username</label><input type="text" name="username" id="username">
            </div>
            <div class="input-field">
              <label for="password">Password</label><input type="password" name="password" id="password">
            </div>
            <div class="input-field">
              <label for="confirm">Confirm Password</label><input type="password" name="confirm" id="confirm">
            </div>
            <button class="btn waves-effect waves-light" type="submit" name="action">
              Sign Up
              <i class="material-icons right">create</i>
            </button>
        </form>
      </div>
    </div>
    <%
      if(request.getParameter("message")!= null){
        out.println(request.getParameter("message"));
      }
    %>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
