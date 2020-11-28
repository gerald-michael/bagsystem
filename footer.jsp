    </main>
    <footer class="page-footer gradient-45deg-light-blue-cyan" id="footer">
      <div class="footer-copyright">
        <div class="container">
          <span>Copyright ©
            <script type="text/javascript">
                document.write(new Date().getFullYear());
            </script> All rights reserved.</span>
          <span class="right hide-on-small-only"> Design and Developed by <a class="grey-text text-lighten-2" href="https://pixinvent.com/">GROUP A</a></span>
        </div>
      </div>
    </footer>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/materialize.js"></script>
    <script>
        $(document).ready(function(){
          $('select').formSelect();
          $('.sidenav').sidenav();
          $('.materialboxed').materialbox();
          $('.modal').modal();
          $('.tabs').tabs();
          $('.tooltipped').tooltip();
        });
    </script>
    <%
      String message = request.getParameter("message");
      if(message != null){
        message = "\"<span><i class='material-icons left'>done</i>" + message + "</span>\"";
        out.println("<script type='text/javascript'>");
        out.println("var toastHTML = " + message + ";");
        out.println("M.toast({html: toastHTML, classes:'green'});");
        out.println("</script>");
      }
      String error = request.getParameter("error");
      if(error != null){
        error = "\"<span><i class='material-icons left'>warning</i>" + error + "</span>\"";
        out.println("<script type='text/javascript'>");
        out.println("var toastHTML = " + error + ";");
        out.println("M.toast({html: toastHTML, classes:'red'});");
        out.println("</script>");
      }
    %>
  </body>
</html>