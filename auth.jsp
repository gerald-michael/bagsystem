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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <style>
      .container{
        position: relative;
        top: 20vh;
      }
      .changeForm{
        text-decoration: underline;
      }
      .input-field input:focus + label {
        color: blue !important;
      }
      .input-field input:focus {
        border-bottom: 1px solid blue !important;
        box-shadow: 0 1px 0 0 blue !important;
      }
    </style>
    <title>BagSystem</title>
  </head>
  <body>
    <div class="container" style="display: none;" id="signup">
      <div class="card-panel">
        <h4 class="header2">Create Account</h4>
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
          <p id="gotologin" class="changeForm">Already have an account, go to login?</p>
          <button class="btn waves-effect waves-light blue" type="submit" name="action">create
            <i class="material-icons right">create</i>
          </button>
        </form>
      </div>
    </div>
    <div class="container" id="login">
      <div class="card-panel">
        <h4 class="header2">Login</h4>
          <form action="login" method="post">
          <div class="input-field">
            <i class="material-icons prefix">account_circle</i>
            <label for="username">Username</label><input type="text" name="username" id="username">
          </div>
          <div class="input-field">
            <i class="material-icons prefix">lock</i>
            <label for="password">Password</label><input type="password" name="password" id="password">
          </div>
          <p id="gotocreate" class="changeForm">Don't have an account, go to create?</p>
          <button class="btn waves-effect waves-light blue" type="submit" name="action">Submit
            <i class="material-icons right">login</i>
          </button>
        </form> 
      </div>
    </div>
    <script type="text/javascript" src="js/materialize.js"></script>
    <script>
      document.addEventListener('DOMContentLoaded', function() {
        var signup = document.querySelector("#signup");
        var login = document.querySelector("#login");

        document.querySelector("#gotocreate").onclick = () =>{
          login.style.display='none';
          signup.style.display="block"
        }
        document.querySelector("#gotologin").onclick = () =>{
          login.style.display='block';
          signup.style.display="none"
        }
      });
    </script>
  </body>
</html>
