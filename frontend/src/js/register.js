import '../css/global.css';
import ConfigApp from './config.js';
import RequestService from './service/RequestService.js';
import RegisterForm from './components/RegisterForm.js';
import {render} from 'react-dom';
import React from 'react';

const config = new ConfigApp();
const requestService = new RequestService(config.getRestUrl());

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function () {
        navigator.serviceWorker
            .register('/service-worker.js')
            .then(res => console.log('service worker registered'))
            .catch(err => console.log('service worker not registered', err));
    });
}

render(<RegisterForm requestService={requestService}/>, document.getElementById('root'));