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

    async logout() {
        const response = await this.fetchService.performPostHttpRequest(`${this.restUrl}/auth/logout`, {}, {});
        console.log(response);
        if (this.refreshInterval) {
            clearInterval(this.refreshInterval);
        }
        window.location.href = '/landing.html';
    }

    async refreshToken() {
        const response = await this.fetchService.performPostHttpRequest(`${this.restUrl}/auth/refresh`, {}, {});
        if (!response.ok) {
            await this.logout();
        }
        return response;
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