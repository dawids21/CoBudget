export default class AuthenticationService {
    constructor(requestService) {
        this.requestService = requestService;
    }

    store(token) {
        let jwt = this._parseJwt(token);
        localStorage.setItem('authorization', token);
        localStorage.setItem('authorizationExpires', (jwt.exp * 1000).toString());
    }

    login(form) {
        this.requestService.login(form).then(() => window.location.href = '/').catch(() => alert('Cannot perform login. Please try again'));
    }

    logout() {
        this._clearAuthData();
        window.location.href = '/landing.html';
    }

    refreshToken() {
        this.requestService.refreshToken().catch(() => this.logout());
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