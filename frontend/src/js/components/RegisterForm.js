import React from 'react';
import Form from './common/Form.js';
import LabeledInput from './common/LabeledInput.js';

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
        }, () => {
            if (name === 'password' || name === 'passwordRepeat') {
                this.checkPasswords();
            }
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        const {name, email, password} = this.state;
        this.props.requestService.signUp({
            name,
            email,
            password,
        }).then(() => window.location.href = '/landing.html').catch((err) => {
            if (err.responseCode === 409) {
                this.setState({
                    status: {
                        message: 'Account with this email already exists',
                        error: true,
                    },
                });
            }
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
            <Form onSubmit={(event) => this.handleSubmit(event)} returnPage="./landing.html"
                  buttonDisabled={this.state.buttonDisabled} buttonText="Sign up">
                <LabeledInput inputName="name" label="Name" type="text" onChange={(e) => this.handleInputChange(e)}/>
                <LabeledInput inputName="email" label="Email" type="email" required
                              onChange={(e) => this.handleInputChange(e)}/>
                <LabeledInput inputName="password" label="Password" type="password" required
                              onChange={(e) => this.handleInputChange(e)}/>
                <LabeledInput inputName="passwordRepeat" label="Repeat password" type="password"
                              onChange={(e) => this.handleInputChange(e)}/>
                <StatusMessage message={this.state.status.message} error={this.state.status.error}/>
            </Form>
        );
    }
}

export default RegisterForm;