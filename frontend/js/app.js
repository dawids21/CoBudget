import JwtService from "./service/JwtService.js";

function getPageName() {
    let path = window.location.pathname;
    return path.split("/").pop();
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

const jwtService = new JwtService();

function addEventListeners() {
    addEventListener("logout-button", "click", jwtService.logout);
    addEventListener("signup-password", "keyup", checkPasswords);
    addEventListener("signup-password-repeat", "keyup", checkPasswords);

    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', function (e) {
            submitSignUpForm(e, this);
        });
    }
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', function (e) {
            submitLoginForm(e, this);
        });
    }
}

if (["", "login.html", "register.html"].includes(getPageName())) {
    window.onload = () => {
        if (!jwtService.checkExpire()) {
            window.location.href = "/week.html";
        }
    };
}

addEventListeners();