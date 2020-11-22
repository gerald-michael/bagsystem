function bing(){
   let el = document.getElementById('stock');
   const cln = el.cloneNode(true);
   document.getElementById('items').appendChild(cln);
}


// products
document.querySelector('#signup_flow').style.display = 'none';

function view_product(){
   
   document.querySelector('#login').onclick = ()=>{
       document.querySelector('#signup_flow').style.display = 'none';
       document.querySelector('#login_flow').style.display = 'block';
       // alert("alive");
   };
   document.querySelector('#signup').onclick = ()=>{
       document.querySelector('#login_flow').style.display = 'none';
       document.querySelector('#signup_flow').style.display = 'block';
       
   };
}
