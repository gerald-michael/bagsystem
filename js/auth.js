document.querySelector('#signup_flow').style.display = 'none';
// document.querySelector('#login_flow').style.display = 'block';

function toggle(){
   
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
