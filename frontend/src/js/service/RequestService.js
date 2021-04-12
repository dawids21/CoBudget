import FetchService from './FetchService.js';
import ResponseError from './ResponseError.js';

export default class RequestService {

    constructor(restUrl, authenticationService) {
        this.restUrl = restUrl;
        this.fetchService = new FetchService();
        this.authenticationService = authenticationService;
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
            response = await this._retryRequest(fetch);
        }

        if (!response.ok) {
            throw new ResponseError(`Fetch error`, response.status);
        }
        return await response.json();
    }

    async getConfig() {
        const fetch = () => this.fetchService.performGetHttpRequest(`${this.restUrl}/user/config`, {});
        let response = await fetch();

        if (response.status === 401) {
            response = await this._retryRequest(fetch);
        }

        if (!response.ok) {
            throw new ResponseError(`Fetch error`, response.status);
        }
        return await response.json();
    }

    async saveConfig(form) {
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const fetch = () => this.fetchService.performPostHttpRequest(this.restUrl + '/user/config', headers, jsonFormData);
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

    async _retryRequest(request) {
        const refreshResponse = await this.authenticationService.refreshToken();
        if (!refreshResponse.ok) {
            return refreshResponse;
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
