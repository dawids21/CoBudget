import JwtService from "./service/JwtService.js";

function getPageName() {
    let path = window.location.pathname;
    return path.split("/").pop();
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
    const logoutButton = document.getElementById("logout-button");
    if (logoutButton) {
        logoutButton.addEventListener("click", jwtService.logout);
    }

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