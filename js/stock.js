function bing(){
   let el = document.getElementById('stock');
   const cln = el.cloneNode(true);
   document.getElementById('items').appendChild(cln);
}