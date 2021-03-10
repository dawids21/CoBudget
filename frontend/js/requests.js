import FetchService from "./service/FetchService.js";
import JwtService from "./service/JwtService.js";

const fetchService = new FetchService();
const jwtService = new JwtService();

async function submitSignUpForm(e, form) {
    e.preventDefault();
    const btnSubmit = document.getElementById('sign-up-submit');
    btnSubmit.disabled = true;
    setTimeout(() => btnSubmit.disabled = false, 2000);
    const jsonFormData = buildJsonFormData(form);
    const headers = buildHeaders();
    try {
        const response = await fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/sign-up', headers, jsonFormData);
        alert(`Hello ${response.name ? response.name : "user"}! Now you can login`);
    } catch (e) {
        alert("Cannot perform sign up. Please try again");
        return;
    }
    window.location.href = "/";
}

async function submitLoginForm(e, form) {
    e.preventDefault();
    const btnSubmit = document.getElementById('sign-in-submit');
    btnSubmit.disabled = true;
    setTimeout(() => btnSubmit.disabled = false, 2000);
    const jsonFormData = buildJsonFormData(form);
    const headers = buildHeaders();
    try {
        const response = await fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/login', headers, jsonFormData);
        jwtService.store(response.token);
    } catch (e) {
        alert("Cannot perform login. Please try again");
    }
}

function buildJsonFormData(form) {
    const jsonFormData = {};
    for (const pair of new FormData(form)) {
        jsonFormData[pair[0]] = pair[1];
    }
    return jsonFormData;
}

function buildHeaders(auth = null) {
    const result = {
        "Content-Type": "application/json",
    };
    if (auth) {
        result.Authorization = auth;
    }
    return result;
}