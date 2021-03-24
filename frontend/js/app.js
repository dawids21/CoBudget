import JwtService from './service/JwtService.js';
import RequestService from './service/RequestService.js';
import ConfigApp from './config.js';
import WeekView from './week.js';
import GetExpensesService from './service/GetExpensesService.js';

const config = new ConfigApp('prod');
const jwtService = new JwtService();
const requestService = new RequestService(config.getRestUrl());

function checkPasswords() {
    if (document.getElementById('signup-password').value ===
        document.getElementById('signup-password-repeat').value) {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--main-green');
        document.getElementById('password-message').innerHTML = 'Passwords are the same';
        document.getElementById('sign-up-submit').disabled = false;
    } else {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--claret');

        document.getElementById('password-message').innerHTML = 'Passwords are not the same';
        document.getElementById('sign-up-submit').disabled = true;
    }
}

function addEventListener(id, eventName, func) {
    const element = document.getElementById(id);
    if (element) {
        element.addEventListener(eventName, func);
    }
}

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/serviceWorker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}

function addEventListeners() {
    addEventListener('logout-button', 'click', () => jwtService.logout());
    addEventListener('signup-password', 'keyup', () => checkPasswords());
    addEventListener('signup-password-repeat', 'keyup', () => checkPasswords());

    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', function (e) {
            requestService.submitSignUpForm(e, this).then(() => window.location.href = '/').catch((err) => {
                if (err.responseCode === 409) {
                    const errorMessage = document.getElementById('error-message');
                    if (errorMessage) {
                        errorMessage.innerText = 'Account with this email already exists';
                        errorMessage.style.color = getComputedStyle(document.documentElement).getPropertyValue('--claret');
                        return;
                    }
                }
                alert('Cannot perform sign up. Please try again');
            });
        });
    }
    const addExpenseForm = document.getElementById('add-expense-form');
    if (addExpenseForm) {
        addExpenseForm.addEventListener('submit', function (e) {
            requestService.submitExpenseForm(e, this).then(() => {
                alert('Expense added!');
                window.location.href = '/week.html';
            }).catch(() => alert('Cannot add expense. Please try again'));
        });
    }
}

function weekViewSetup() {
    const weekViewElement = document.getElementById('week-view');
    if (!weekViewElement) {
        return;
    }
    const getExpenseService = new GetExpensesService(requestService);
    const weekView = new WeekView(getExpenseService);
}

addEventListeners();
weekViewSetup();