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
    <h1>Products</h1>
    <form action="create" method="post">
      <label for="name">Name</label><input type="text" name="name" id="name">
      <label for="description">Description</label><input type="text" name="description" id="description">
      <input type="submit" value="create">
    </form>
    <script type="text/javascript" src="js/materialize.js"></script>
  </body>
</html>
