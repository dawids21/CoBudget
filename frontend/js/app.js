const
    checkPasswords = () => {
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
    }

document.getElementById('password').addEventListener('keyup',
    checkPasswords)
document.getElementById('password-repeat').addEventListener('keyup',
    checkPasswords)
