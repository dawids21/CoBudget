import FetchService from './FetchService.js';
import AuthenticationService from './AuthenticationService.js';
import ResponseError from './ResponseError.js';

export default class RequestService {

    constructor(restUrl) {
        this.restUrl = restUrl;
        this.fetchService = new FetchService();
        this.authenticationService = new AuthenticationService();
    }

    async signUp(form) {
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const response = await this.fetchService.performPostHttpRequest(this.restUrl + '/user/sign-up', headers, jsonFormData);
        if (!response.ok) {
            throw new ResponseError(`HTTP error! status: ${response.status}`, response.status);
        }
        const jsonResponse = await response.json();
        alert(`Hello ${jsonResponse.name ? jsonResponse.name : 'user'}! Now you can login`);
    }

    async login(form) {
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        return await this.fetchService.performPostHttpRequest(this.restUrl + '/auth/login', headers, jsonFormData);
    }

    async addExpense(form) {
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const fetch = () => this.fetchService.performPostHttpRequest(this.restUrl + '/expense', headers, jsonFormData);
        let response = await fetch();

        if (response.status === 401) {
            try {
                response = await this._retryRequest(fetch);
            } catch {
                return;
            }
        }

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
    }

    async getMonthlyExpenses(month, year) {
        const headers = this._buildHeaders();
        const fetch = () => this.fetchService.performGetHttpRequest(`${this.restUrl}/expense?month=${month}&year=${year}`, headers);
        let response = await fetch();

        if (response.status === 401) {
            try {
                response = await this._retryRequest(fetch);
            } catch {
                return;
            }
        }

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();
    }

    async refreshToken() {
        return await this.fetchService.performPostHttpRequest(`${this.restUrl}/auth/refresh`);
    }

    async _retryRequest(request) {
        try {
            await this.authenticationService.refreshToken();
        } catch (e) {
            throw e;
        }
        return await request();
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
