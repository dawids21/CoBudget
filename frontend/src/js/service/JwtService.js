export default class JwtService {
    constructor() {
    }

    store(token) {
        let jwt = this._parseJwt(token);
        localStorage.setItem("authorization", token);
        localStorage.setItem("authorizationExpires", (jwt.exp * 1000).toString());
    }

    checkExpire() {
        let exp = localStorage.getItem("authorizationExpires");
        let now = new Date();
        return now >= new Date(+exp);
    }

    logout() {
        this.clearAuthData();
        window.location.href = "/";
    }

    getToken() {
        return localStorage.getItem("authorization");
    }

    clearAuthData() {
        localStorage.removeItem("authorization");
        localStorage.removeItem("authorizationExpires");
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