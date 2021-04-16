import React from 'react';

const StatusMessage = (props) => {
    return <span className="margin-top--xs"
                 style={{color: props.error ? 'var(--claret)' : 'var(--main-green)'}}>{props.message}</span>;
};

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
            buttonDisabled: false,
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
        if (this.state.password === this.state.passwordRepeat) {
            this.setState({
                status: {
                    message: 'Passwords are the same',
                    error: false,
                },
                buttonDisabled: false,
            });
        } else {
            this.setState({
                status: {
                    message: 'Passwords are not the same',
                    error: true,
                },
                buttonDisabled: true,
            });
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
                        <StatusMessage message={this.state.status.message} error={this.state.status.error}/>
                    </div>
                    <input type="submit" className="button disable-select" name="sign-up"
                           value="Sign up" disabled={this.state.buttonDisabled}/>
                </form>
            </div>
        );
    }
}

export default RegisterForm;