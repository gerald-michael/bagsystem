<%@ page language="java" contentType="text/html" isErrorPage="true"%>
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
    <h1>Error</h1>
    <%-- exception is an object that only works in the error page --%>
    <%=
        exception.getMessage()
    %>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
