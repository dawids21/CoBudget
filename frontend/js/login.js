import FetchService from "./service/FetchService.js";

const fetchService = new FetchService();

async function submitForm(e, form) {
    e.preventDefault();
    const btnSubmit = document.getElementById('sign-in-submit');
    btnSubmit.disabled = true;
    setTimeout(() => btnSubmit.disabled = false, 2000);
    const jsonFormData = buildJsonFormData(form);
    const headers = buildHeaders();
    try {
        const response = await fetchService.performPostHttpRequest('http://localhost:8080/user/login', headers, jsonFormData);
        localStorage.setItem('token', response.token);
    } catch (e) {
        alert("Cannot perform login. Please try again");
        return;
    }
    // window.location.href = "/monthView.html";
}

function buildJsonFormData(form) {
    const jsonFormData = {};
    for (const pair of new FormData(form)) {
        jsonFormData[pair[0]] = pair[1];
    }
    return jsonFormData;
}

function buildHeaders() {
    return {
        "Content-Type": "application/json",
    };
}

const form = document.getElementById('login-form');
form.addEventListener('submit', function (e) {
    submitForm(e, this);
});
