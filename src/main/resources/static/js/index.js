const btns = document.getElementsByTagName("button");

for(let i=0; i<btns.length; i++)
    switch(btns.item(i).innerText) {
        case "Cadastrar":
            btns.item(i).onclick = () => {location.href="/new/"+document.getElementsByTagName("input").item(0).value}
            break;
        case "Editar":
            btns.item(i).onclick = () => {location.href="/edit/"+document.getElementsByTagName("input").item(0).value}
            break;
        case "Remover":
            btns.item(i).onclick = () => {location.href="/del/"+document.getElementsByTagName("input").item(0).value}
    }