import FetchService from "./FetchService.js";
import JwtService from "./JwtService.js";

export default class RequestService {

    constructor() {
        this.fetchService = new FetchService();
        this.jwtService = new JwtService();
    }

    async submitSignUpForm(e, form) {
        e.preventDefault();
        const btnSubmit = document.getElementById('sign-up-submit');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const response = await this.fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/sign-up', headers, jsonFormData);
        const jsonResponse = await response.json();
        alert(`Hello ${jsonResponse.name ? jsonResponse.name : "user"}! Now you can login`);
    }

    async submitLoginForm(e, form) {
        e.preventDefault();
        const btnSubmit = document.getElementById('sign-in-submit');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        const response = await this.fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/login', headers, jsonFormData);
        const jsonResponse = await response.json();
        this.jwtService.store(jsonResponse.token);
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
            "Content-Type": "application/json",
        };
        if (auth) {
            result.Authorization = auth;
        }
        return result;
    }
}
