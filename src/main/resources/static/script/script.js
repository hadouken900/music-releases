document.getElementById('button').onclick =

function loadData(){
    const request = new XMLHttpRequest();
    const url = "/ajaxrefresh";
    request.open('GET', url);

    request.addEventListener("load", () => {
    if (request.status === 200) {

        console.log( request.responseText );
        }
    });
    request.send();
}