import ResponseError from './ResponseError.js';

export default class AuthenticationService {
    constructor(requestService) {
        this.requestService = requestService;
    }

    async login(form) {
        let response;
        try {
            response = await this.requestService.login(form);
        } catch (e) {
            throw e;
        }
        this.refreshInterval = setInterval(() => this.refreshToken(), 1800000);
        return response;
    }

    logout() {
        this._clearAuthData();
        if (this.refreshInterval) {
            clearInterval(this.refreshInterval);
        }
        window.location.href = '/landing.html';
    }

    async refreshToken() {
        const response = await this.requestService.refreshToken();
        if (!response.ok) {
            this.logout();
            throw new ResponseError('Refreshing not successful', response.status);
        }
    }


    _clearAuthData() {
        document.cookie = 'accessCookie= ; expires = Thu, 01 Jan 1970 00:00:00 GMT';
        document.cookie = 'refreshCookie= ; expires = Thu, 01 Jan 1970 00:00:00 GMT';
    }

    _parseJwt(token) {
        let base64Url = token.split('.')[1];
        let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        let jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    };
}