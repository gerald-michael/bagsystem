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

    <link
      href="css/style.css"
      rel="stylesheet"
    />
    <title>BagSystem</title>
     <noscript>
        site needs javascript to run
    </noscript>
    <style>
     main, footer {
      padding-left: 300px;
    }

    @media only screen and (max-width : 992px) {
    main, footer {
        padding-left: 0;
      }
    }
    .sidenav{
      position:relative;
      top:70px;
    }
    #footer { 
            position: fixed; 
            width:100%;
            bottom: 0; 
        }
    </style>
  </head>
  <body>
    <%
      response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
      response.setHeader("Pragma","no-cache");
      response.setHeader("Expires","0");  
    %>
    <header id="header" class="page-topbar">
      <div class="navbar-fixed">
        <nav class="navbar-color gradient-45deg-light-blue-cyan">
          <div class="container"/>
          <div class="nav-wrapper">
            <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
            <ul class="left">
              <li>
                <h1 class="logo-wrapper">
                  <a href="index.jsp" class="brand-logo darken-1">
                    <span class="logo-text">BagSystem</span>
                  </a>
                </h1>
              </li>
            </ul>
            <ul class="right">
              <li>
                <a href="profile.jsp" class="waves-effect waves-block waves-light profile-button tooltipped" data-position="bottom" data-tooltip="profile">
                  <span class="avatar-status avatar-online">
                    <%
                      String username = session.getAttribute("username").toString();
                      AuthenticationDao authenticationDao = new AuthenticationDao();
                      User userx = new User();
                      userx.setUsername(username);
                      userx = authenticationDao.getUser(userx);
                      out.println("<img src='../portal/profile/"+userx.getProfileImage()+"' alt='avatar'>");
                    %>
                    <i></i>
                  </span>
                </a>
              </li>
              <li>
                <a href="logout" data-activates="chat-out" data-position="bottom" class="waves-effect waves-block waves-light tooltipped chat-collapse" data-tooltip="logout">
                  <i class="material-icons">format_indent_increase</i>
                </a>
              </li>
            </ul>
          </div>
          </div>
        </nav>
      </div>
      <!-- end header nav-->
    </header>
  <main>