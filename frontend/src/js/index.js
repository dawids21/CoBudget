import '../css/style.css';
import AuthenticationService from './service/AuthenticationService.js';

const jwtService = new AuthenticationService();

if (!jwtService.checkExpire()) {
    window.location.href = '/week.html';
}

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/service-worker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}
