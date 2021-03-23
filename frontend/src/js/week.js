import '../css/style.css';
import '../css/fontawesome/all.min.css';
import JwtService from './service/JwtService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import GetExpensesService from './service/GetExpensesService.js';
import WeekView from './components/WeekView.js';

const config = new ConfigApp('prod');
const jwtService = new JwtService();
const requestService = new RequestService(config.getRestUrl());

if (jwtService.checkExpire()) {
    jwtService.logout();
}

//if ("serviceWorker" in navigator) {
//    window.addEventListener("load", function () {
//        navigator.serviceWorker
//            .register("/serviceWorker.js")
//            .then(res => console.log("service worker registered"))
//            .catch(err => console.log("service worker not registered", err));
//    });
//}

document.getElementById('logout-button').addEventListener('click', () => jwtService.logout());
const getExpenseService = new GetExpensesService(requestService);
new WeekView(getExpenseService);
