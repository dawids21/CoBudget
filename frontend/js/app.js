import JwtService from "./service/JwtService.js";
import RequestService from "./service/RequestService.js";

const jwtService = new JwtService();
const requestService = new RequestService();

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

if ("serviceWorker" in navigator) {
    window.addEventListener("load", function () {
        navigator.serviceWorker
            .register("/serviceWorker.js")
            .then(res => console.log("service worker registered"))
            .catch(err => console.log("service worker not registered", err));
    });
}

function addEventListeners() {
    addEventListener("logout-button", "click", jwtService.logout);
    addEventListener("signup-password", "keyup", checkPasswords);
    addEventListener("signup-password-repeat", "keyup", checkPasswords);

    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', function (e) {
            requestService.submitSignUpForm(e, this).then(() => window.location.href = "/").catch(() => alert("Cannot perform sign up. Please try again"));
        });
    }
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', function (e) {
            requestService.submitLoginForm(e, this).then(() => window.location.href = "/week.html").catch(() => alert("Cannot perform login. Please try again"));
        });
    }
}

addEventListeners();