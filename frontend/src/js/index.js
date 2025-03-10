import '../css/style.css';
import AuthenticationService from './service/AuthenticationService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import GetExpensesService from './service/GetExpensesService.js';
import WeekView from './components/WeekView.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faCaretLeft, faCaretRight, faCog, faPlus} from '@fortawesome/free-solid-svg-icons';
import FetchService from './service/FetchService.js';

const config = new ConfigApp();
const authenticationService = new AuthenticationService(new FetchService(), config.getRestUrl());
const requestService = new RequestService(config.getRestUrl(), authenticationService);

library.add(faPlus, faCog, faCaretLeft, faCaretRight);
dom.watch();

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/service-worker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}

document.getElementById('logout-button').addEventListener('click', async () => await authenticationService.logout());
const getExpenseService = new GetExpensesService(requestService);
new WeekView(getExpenseService);
