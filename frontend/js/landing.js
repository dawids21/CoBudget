import FetchService from "./service/FetchService.js";

const fetchService = new FetchService();

function checkPasswords() {
    if (document.getElementById('signup-password').value ===
        document.getElementById('signup-password-repeat').value) {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--main-green');
        document.getElementById('password-message').innerHTML = 'Passwords are the same';
        document.getElementById('sign-up-submit').disabled = false;
    } else {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--claret');

        document.getElementById('password-message').innerHTML = 'Passwords are not the same';
        document.getElementById('sign-up-submit').disabled = true;
    }
}

async function submitSignUpForm(e, form) {
    e.preventDefault();
    const btnSubmit = document.getElementById('sign-up-submit');
    btnSubmit.disabled = true;
    setTimeout(() => btnSubmit.disabled = false, 2000);
    const jsonFormData = buildJsonFormData(form);
    const headers = buildHeaders();
    try {
        const response = await fetchService.performPostHttpRequest('https://cobudget-backend.herokuapp.com/user/sign-up', headers, jsonFormData);
        const jsonResponse = response.json();
        alert(`Hello ${jsonResponse.name ? jsonResponse.name : "user"}! Now you can login`);
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
        localStorage.setItem('token', response.token);
    } catch (e) {
        alert("Cannot perform login. Please try again");
        return;
    }
    window.location.href = "/monthView.html";
}

document.getElementById('signup-password')?.addEventListener('keyup',
    checkPasswords);
document.getElementById('signup-password-repeat')?.addEventListener('keyup',
    checkPasswords);

const registerForm = document.getElementById('register-form');
registerForm?.addEventListener('submit', function (e) {
    submitSignUpForm(e, this);
});

const loginForm = document.getElementById('login-form');
loginForm?.addEventListener('submit', function (e) {
    submitLoginForm(e, this);
});

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
