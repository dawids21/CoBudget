import '../css/style.css';
import AuthenticationService from './service/AuthenticationService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';

const config = new ConfigApp();
const jwtService = new AuthenticationService();
const requestService = new RequestService(config.getRestUrl());

if (!jwtService.checkExpire()) {
    window.location.href = '/week.html';
}

library.add(faAngleLeft);
dom.watch();

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/service-worker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}

const loginForm = document.getElementById('login-form');
if (loginForm) {
    loginForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const btnSubmit = document.getElementById('sign-in-submit');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        requestService.login(this).then(() => window.location.href = '/week.html').catch(() => alert('Cannot perform login. Please try again'));
    });
}
