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
