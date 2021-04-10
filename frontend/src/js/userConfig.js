import '../css/pages/user-config.css';
import AuthenticationService from './service/AuthenticationService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faAngleLeft, faCog} from '@fortawesome/free-solid-svg-icons';
import FetchService from './service/FetchService.js';

const config = new ConfigApp();
const authenticationService = new AuthenticationService(new FetchService(), config.getRestUrl());
const requestService = new RequestService(config.getRestUrl(), authenticationService);

library.add(faAngleLeft, faCog);
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

const configForm = document.querySelector('#config-form');
if (configForm) {
    configForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const btnSubmit = document.querySelector('#save-config-button');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        const message = document.querySelector('#message');
        requestService.saveConfig(this).then(() => {
            if (message) {
                message.innerHTML = 'Configuration saved';
                message.style.color = getComputedStyle(document.documentElement).getPropertyValue('--dark-green');
            }
        }).catch(() => {
            if (message) {
                message.innerHTML = 'Error during saving';
                message.style.color = getComputedStyle(document.documentElement).getPropertyValue('--claret');
            }
        });
    });
}

const entryNotificationEnable = document.querySelector('#entry-notification-enable');
if (entryNotificationEnable) {
    entryNotificationEnable.addEventListener('change', () => {
        const entryNotificationTimeOption = document.querySelector('#entry-notification-time-option');
        if (entryNotificationTimeOption) {
            entryNotificationTimeOption.innerHTML = entryNotificationEnable.checked ?
                `
                <label class="margin-top--sm" for="entry-notification-time">Time</label>
                <input type="time" id="entry-notification-time" name="entry-notification-time">
                ` : '&nbsp;';
        }
    });
}