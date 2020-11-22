  <%-- <ul id="slide-out" class="sidenav">
    <li><div class="user-view">
      <div class="background">
        <img src="images/office.jpg">
      </div>
      <a href="#user"><img class="circle" src="images/yuna.jpg"></a>
      <a href="#name"><span class="white-text name">John Doe</span></a>
      <a href="#email"><span class="white-text email">jdandturk@gmail.com</span></a>
    </div></li>
    <li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
    <li><a href="#!">Second Link</a></li>
    <li><div class="divider"></div></li>
    <li><a class="subheader">Subheader</a></li>
    <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
  </ul> --%>
  

  <ul id="menu_priv">
                
  </ul>

  <script>

        // Testing block Menu

        var privs = new Array("<li class='card-panel'><i class='material-icons large'>person</i></li>",
        "<li class='card lighten-2'>Home</li>",
        "<li class='card lighten-2'> Products</li>",
        "<li class='card lighten-2'>Stock</li>",
        "<li class='card lighten-2'>Sale</li>",
        "<li class='card lighten-2'>Reports</li>",
        "<li class='card lighten-2'>Statistics</li>",
        "<li class='card lighten-2'>Customers</li>",
        "<li class='card-panel'><i class='material-icons'>logout</i><br>logout</li>")

        var priv = new Array("<li class='card-panel'><i class='material-icons large'>person</i></li>",
        "<li class='card lighten-2'>Home</li>",
        "<li class='card lighten-2'> Products</li>",
        "<li class='card lighten-2'>Stock</li>",
        "<li class='card lighten-2'>Sale</li>",
        "<li class='card-panel'><i class='material-icons'>logout</i><br>logout</li>")

        let user = "admin";
        //function user_side_nav(user){
            if(user == "admin"){
                let dac = ''
                for(i in privs){
                    dac+=privs[i];
                }
                document.getElementById('menu_priv').innerHTML = `${dac}`;
            }else{
                let dac = ''
                for(i in priv){
                    dac+=priv[i];
                }
                document.getElementById('menu_priv').innerHTML = `${dac}`;
            }
        //}

       // function get_user(){
       //     user_side_nav("admin")
     //   }
    </script>