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

const logoutButton = document.getElementById("logout-button");

if (logoutButton !== null) {
    logoutButton.addEventListener("click", jwtService.logout);
}

if (["", "login.html", "register.html"].includes(getPageName())) {
    window.onload = () => {
        if (!jwtService.checkExpire()) {
            window.location.href = "/week.html";
        }
    };
}