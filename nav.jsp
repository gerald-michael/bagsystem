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
      header,main, footer {
      padding-left: 300px;
    }

    @media only screen and (max-width : 992px) {
      header,main, footer {
        padding-left: 0;
      }
    }
    .sidenav{
      margin-top:10 important!;
    }
    </style>
  </head>
  <body>
  <header>
    <nav>
    <div class="nav-wrapper">
      <div class="container">
      <a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
      <a href="#" class="brand-logo">BagSystem</a>
      <%-- <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="sass.html">Sass</a></li>
        <li><a href="badges.html">Components</a></li>
        <li><a href="collapsible.html">JavaScript</a></li>
      </ul> --%>
      </div>
    </div>
  </nav>
  </header>
  <main>