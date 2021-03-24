import '../css/style.css';
import JwtService from './service/JwtService.js';

const jwtService = new JwtService();

if (!jwtService.checkExpire()) {
    window.location.href = '/week.html';
}

//if ("serviceWorker" in navigator) {
//    window.addEventListener("load", function () {
//        navigator.serviceWorker
//            .register("/serviceWorker.js")
//            .then(res => console.log("service worker registered"))
//            .catch(err => console.log("service worker not registered", err));
//    });
//}
