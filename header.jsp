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
     <noscript>
        site needs javascript to run
    </noscript>
  </head>
  <body onload="get_user()">
    <%
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Expires","0");  
    %>

    <%-- header --%>
      <header class="grey center lighten5">
        <div class="row">
            <div class="col s3">
                <i class="material-icons large">store</i>
                <div id="toggle_menu" class="btn waves-effect waves-light"><i class="material-icons medium">menu</i></div>
            </div>
            <div class="col s9">
                <div class="row">
                    <div class="col s12"><span class="flow-text">Bag invetory System</span></div>
                    <div class="col s6 offset-s6 right"><span class="flow-text">
                        <i class="material-icons">notifications</i>
                        <i class="material-icons">mail</i>
                        <i class="material-icons">check_out</i>
                    </span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <%-- navbar --%>

    <section class="row" style="display: relative;padding-top:0">
      <!-- Aside navbar of menu -->
      <div class="col grey darken-2 s3 center">
          <ul id="menu_priv">
              
          </ul>
      </div>

      <div class="col s9">
      
