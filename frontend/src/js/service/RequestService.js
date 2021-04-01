import FetchService from './FetchService.js';
import AuthenticationService from './AuthenticationService.js';
import ResponseError from './ResponseError.js';

export default class RequestService {

    constructor(restUrl) {
        this.restUrl = restUrl;
        this.fetchService = new FetchService();
        this.jwtService = new AuthenticationService();
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
        const response = await this.fetchService.performPostHttpRequest(this.restUrl + '/user/login', headers, jsonFormData);
        const jsonResponse = await response.json();
        this.jwtService.store(jsonResponse.token);
    }

    async submitExpenseForm(e, form) {
        e.preventDefault();
        const btnSubmit = document.getElementById('add-expense-button');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders(this.jwtService.getToken());
        const response = await this.fetchService.performPostHttpRequest(this.restUrl + '/expense', headers, jsonFormData);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
    }

    async getMonthlyExpenses(month, year) {
        const headers = this._buildHeaders(this.jwtService.getToken());
        const response = await this.fetchService.performGetHttpRequest(`${this.restUrl}/expense?month=${month}&year=${year}`, headers);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return await response.json();
    }

    async refreshToken() {
        const response = await this.fetchService.performPostHttpRequest(`${this.restUrl}/auth/refresh`);
        if (!response.ok) {
            throw new ResponseError('Refreshing not successful', response.status);
        }
    }

    _buildJsonFormData(form) {
        const jsonFormData = {};
        for (const pair of new FormData(form)) {
            jsonFormData[pair[0]] = pair[1];
        }
        return jsonFormData;
    }

    _buildHeaders(auth = null) {
        const result = {
            'Content-Type': 'application/json',
        };
        if (auth) {
            result.Authorization = `Bearer ${auth}`;
        }
        return result;
    }
}
