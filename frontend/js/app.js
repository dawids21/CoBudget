import JwtService from "./service/JwtService.js";

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

if (logoutButton !== undefined) {
    logoutButton.addEventListener("click", jwtService.logout);
}