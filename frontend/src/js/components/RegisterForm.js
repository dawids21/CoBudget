import React from 'react';

class RegisterForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            password: '',
            passwordRepeat: '',
            status: {
                message: '',
                error: false,
            },
        };
    }

    handleInputChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            [name]: value,
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.requestService.signUp(document.getElementById('register-form')).then(() => window.location.href = '/landing.html').catch((err) => {
            if (err.responseCode === 409) {
                const errorMessage = document.getElementById('error-message');
                if (errorMessage) {
                    errorMessage.innerText = 'Account with this email already exists';
                    errorMessage.style.color = getComputedStyle(document.documentElement).getPropertyValue('--claret');
                    return;
                }
            }
            alert('Cannot perform sign up. Please try again');
        });
    }

    checkPasswords() {
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

    render() {
        return (
            <div className="container-column">
                <div className="logo-container">
                    <h1 className="logo disable-select">CoBudget</h1>
                </div>
                <a id="back-button" className="icon-button" href="./landing.html"><i
                    className="fas fa-angle-left fa-3x"/></a>
                <form id="register-form" className="form" onSubmit={(event) => this.handleSubmit(event)}>
                    <span id="error-message"/>
                    <div className="form-data">
                        <label htmlFor="name">Name <span className="text--sm">(optional)</span>:</label>
                        <input className="form-text" type="text" id="name" name="name"
                               onChange={(e) => this.handleInputChange(e)}/>
                        <label className="margin-top--sm" htmlFor="email">Email:</label>
                        <input className="form-text" type="email" id="email" name="email"
                               onChange={(e) => this.handleInputChange(e)} required/>
                        <label className="margin-top--sm" htmlFor="signup-password">Password:</label>
                        <input className="form-text" type="password" id="signup-password" name="password"
                               onChange={(e) => this.handleInputChange(e)} onKeyUp={() => this.checkPasswords()}
                               required/>
                        <label className="margin-top--sm" htmlFor="signup-password-repeat">Repeat
                            password:</label>
                        <input className="form-text" type="password" id="signup-password-repeat"
                               name="passwordRepeat" onChange={(e) => this.handleInputChange(e)}
                               onKeyUp={() => this.checkPasswords()}
                               required/>
                        <span id="password-message" className="margin-top--xs">&nbsp;</span>
                    </div>
                    <input id="sign-up-submit" type="submit" className="button disable-select" name="sign-up"
                           value="Sign up"/>
                </form>
            </div>
        );
    }
}

export default RegisterForm;