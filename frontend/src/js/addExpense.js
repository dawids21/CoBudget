import '../css/style.css';
import AuthenticationService from './service/AuthenticationService.js';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faAngleLeft, faBars} from '@fortawesome/free-solid-svg-icons';

const config = new ConfigApp();
const jwtService = new AuthenticationService();
const requestService = new RequestService(config.getRestUrl());

if (jwtService.checkExpire()) {
    jwtService.logout();
}

library.add(faBars, faAngleLeft);
dom.watch();

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/service-worker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}

document.getElementById('logout-button').addEventListener('click', () => jwtService.logout());

const addExpenseForm = document.getElementById('add-expense-form');
if (addExpenseForm) {
    addExpenseForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const btnSubmit = document.getElementById('add-expense-button');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        requestService.addExpense(this).then(() => {
            alert('Expense added!');
            window.location.href = '/week.html';
        }).catch(() => alert('Cannot add expense. Please try again'));
    });
}
