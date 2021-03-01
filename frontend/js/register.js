import FetchService from "./service/FetchService.js";

const fetchService = new FetchService();

const checkPasswords = () => {
    if (document.getElementById('password').value ===
        document.getElementById('password-repeat').value) {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--main-green');
        document.getElementById('password-message').innerHTML = 'Passwords are the same';
        document.getElementById('sign-in-submit').disabled = false;
    } else {
        document.getElementById('password-message').style.color = getComputedStyle(document.documentElement)
            .getPropertyValue('--claret');

        document.getElementById('password-message').innerHTML = 'Passwords are not the same';
        document.getElementById('sign-in-submit').disabled = true;
    }
};

document.getElementById('password').addEventListener('keyup',
    checkPasswords);
document.getElementById('password-repeat').addEventListener('keyup',
    checkPasswords);

async function submitForm(e, form) {
    e.preventDefault();
    const btnSubmit = document.getElementById('sign-in-submit');
    btnSubmit.disabled = true;
    setTimeout(() => btnSubmit.disabled = false, 2000);
    const jsonFormData = buildJsonFormData(form);
    const headers = buildHeaders();
    try {
        const response = await fetchService.performPostHttpRequest('http://localhost:8080/user/sign-up', headers, jsonFormData);
        const jsonResponse = response.json();
        alert(`Hello ${jsonResponse.name ? jsonResponse.name : "user"}! Now you can login`);
    } catch (e) {
        alert("Cannot perform sign up. Please try again");
        return;
    }
    window.location.href = "/";
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

const form = document.getElementById('register-form');
form.addEventListener('submit', function (e) {
    submitForm(e, this);
});
