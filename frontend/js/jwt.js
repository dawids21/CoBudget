import JwtService from "./service/JwtService.js";

let service = new JwtService();

function checkJwt() {
    if (service.checkExpire()) {
        service.logout();
    }
}

window.onload = () => checkJwt();