
//JS FOR MAIN PAGE

const main = document.getElementById('result');
const filtAll = document.getElementById('filtAll');
const button = document.getElementById('refr');


//AJAX REQUEST FOR FILTERING
filtAll.addEventListener('input', () =>
{
    const request = new XMLHttpRequest();
    const url = "/ajaxxxfilt?filter=" + filtAll.value;
    request.open('GET', url);

    request.addEventListener("load", () => {
    if (request.status === 200) {

        console.log( request.responseText );
        main.innerHTML=request.responseText;
        }
        else {
        console.log('Что то пошло не так');
        }
    });
    request.send();
});

//AJAX REQUEST FOR REFRESH LIST OF ALBUMS
button.addEventListener('click',() =>{
    const request = new XMLHttpRequest();
    const url = "/ajaxxx";
    request.open('GET', url);

    request.addEventListener("load", () => {
    if (request.status === 200) {

        console.log( request.responseText );
        //main.innerHTML=request.responseText;
        }
    });
    request.send();
});
