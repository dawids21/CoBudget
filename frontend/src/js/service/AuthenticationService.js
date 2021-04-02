import ResponseError from './ResponseError.js';

export default class AuthenticationService {
    constructor(fetchService, restUrl) {
        this.fetchService = fetchService;
        this.restUrl = restUrl;
    }

    async login(form) {
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const response = await this.fetchService.performPostHttpRequest(this.restUrl + '/auth/login', headers, jsonFormData);
        if (!response.ok) {
            throw new ResponseError('Cannot login', response.status);
        }
        this.refreshInterval = setInterval(() => this.refreshToken(), 1800000);
    }

    logout() {
        this._clearAuthData();
        if (this.refreshInterval) {
            clearInterval(this.refreshInterval);
        }
        window.location.href = '/landing.html';
    }

    async refreshToken() {
        const response = await this.fetchService.performPostHttpRequest(`${this.restUrl}/auth/refresh`, {}, {});
        if (!response.ok) {
            this.logout();
        }
        return response;
    }

    _clearAuthData() {
        document.cookie = 'accessCookie= ; expires = Thu, 01 Jan 1970 00:00:00 GMT';
        document.cookie = 'refreshCookie= ; expires = Thu, 01 Jan 1970 00:00:00 GMT';
    }

    _buildJsonFormData(form) {
        const jsonFormData = {};
        for (const pair of new FormData(form)) {
            jsonFormData[pair[0]] = pair[1];
        }
        return jsonFormData;
    }

    _buildHeaders() {
        return {
            'Content-Type': 'application/json',
        };
    }
}