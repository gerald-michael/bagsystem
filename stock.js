
   var el = document.getElementById('items');
   var cln = el.cloneNode(true);
function bing(){
    // var el = document.getElementById('items');
    // var cln = el.cloneNode(true);
    document.getElementById('items').appendChild(cln);
   alert(`${el}`);
}