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
        try {
            const response = await this.fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/sign-up', headers, jsonFormData);
            alert(`Hello ${response.name ? response.name : "user"}! Now you can login`);
        } catch (e) {
            alert("Cannot perform sign up. Please try again");
        }
    }

    async submitLoginForm(e, form) {
        e.preventDefault();
        const btnSubmit = document.getElementById('sign-in-submit');
        btnSubmit.disabled = true;
        setTimeout(() => btnSubmit.disabled = false, 2000);
        const jsonFormData = this._buildJsonFormData(form);
        const headers = this._buildHeaders();
        try {
            const response = await this.fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/login', headers, jsonFormData);
            this.jwtService.store(response.token);
        } catch (e) {
            alert("Cannot perform login. Please try again");
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
            "Content-Type": "application/json",
        };
        if (auth) {
            result.Authorization = auth;
        }
        return result;
    }
}
