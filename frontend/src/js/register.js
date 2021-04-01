import '../css/style.css';
import ConfigApp from './config.js';
import AuthenticationService from './service/AuthenticationService.js';
import RequestService from './service/RequestService.js';
import {dom, library} from '@fortawesome/fontawesome-svg-core';
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';

const config = new ConfigApp();
const jwtService = new AuthenticationService();
const requestService = new RequestService(config.getRestUrl());

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

document.getElementById('signup-password').addEventListener('keyup', () => checkPasswords());
document.getElementById('signup-password-repeat').addEventListener('keyup', () => checkPasswords());

const registerForm = document.getElementById('register-form');
if (registerForm) {
    registerForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const btnSubmit = document.getElementById('sign-up-submit');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        requestService.signUp(this).then(() => window.location.href = '/landing.html').catch((err) => {
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
