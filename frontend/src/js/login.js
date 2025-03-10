import '../css/style.css';
import AuthenticationService from './service/AuthenticationService.js';
import ConfigApp from './config.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';
import FetchService from './service/FetchService.js';

const config = new ConfigApp();
const authenticationService = new AuthenticationService(new FetchService(), config.getRestUrl());

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
        authenticationService.login(this).then(() => window.location.href = '/').catch(() => alert('Cannot perform login. Please try again'));
    });
}
