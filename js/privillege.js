
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
function user_side_nav(user){
    if(user == "admin"){
        let dac = ''
        for(i in privs){
            dac+=privs[i];
        }
        document.getElementById('menu_priv').innerHTML = `${dac}`;
        // for(i in privs){
        //     el.appendNode(`${privs[i]}`);
        // }
    }else{
        let dac = ''
        for(i in priv){
            dac+=priv[i];
        }
        document.getElementById('menu_priv').innerHTML = `${dac}`;
    }
}

function get_user(){
    // let user = prompt("User: ")
    // user_side_nav(prompt("User: ","admin"))
    user_side_nav("admin")
}