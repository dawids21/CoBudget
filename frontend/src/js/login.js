import '../css/style.css';
import '../css/fontawesome/all.min.css';
import JwtService from './service/JwtService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';


const config = new ConfigApp('prod');
const jwtService = new JwtService();
const requestService = new RequestService(config.getRestUrl());

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

const loginForm = document.getElementById('login-form');
if (loginForm) {
    loginForm.addEventListener('submit', function (e) {
        requestService.submitLoginForm(e, this).then(() => window.location.href = '/week.html').catch(() => alert('Cannot perform login. Please try again'));
    });
}
