const API_URL = "http://localhost:8080";
const xhr = new XMLHttpRequest();

function onRequestHandler() {
    if(this.readyState == 4 && this.status == 200) {
        const data = JSON.parse(this.response);
        const HTMLResponse = document.querySelector("#app");
        const tpl = data.map(function(item) {
                                return `<li> <a href='${item.sUrl}'>${item.sName}</a><img src="${item.sPicUrl}"> ${item.sDescription} ${item.price}€</li>`;
                            })
        HTMLResponse.innerHTML = `<ul>${tpl}</ul>`;
    }
}

xhr.addEventListener("load", onRequestHandler);
xhr.open('GET', `${API_URL}/items`);
xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
xhr.send();


