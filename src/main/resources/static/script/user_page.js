
const main = document.getElementById('result');
const filtUser = document.getElementById('filtUser');
filtUser.addEventListener('input', () =>
{
    const request = new XMLHttpRequest();
    const url = "/ajaxxxfiltuser?filter=" + filtUser.value;
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