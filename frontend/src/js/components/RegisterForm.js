import React from 'react';

export default class RegisterForm extends React.Component {
    render() {
        return (
            <div className="container-column">
                <div className="logo-container">
                    <h1 className="logo disable-select">CoBudget</h1>
                </div>
                <a id="back-button" className="icon-button" href="./landing.html"><i
                    className="fas fa-angle-left fa-3x"/></a>
                <form id="register-form" className="form">
                    <span id="error-message"/>
                    <div className="form-data">
                        <label htmlFor="name">Name <span className="text--sm">(optional)</span>:</label>
                        <input className="form-text" type="text" id="name" name="name"/>
                        <label className="margin-top--sm" htmlFor="email">Email:</label>
                        <input className="form-text" type="email" id="email" name="email" required/>
                        <label className="margin-top--sm" htmlFor="signup-password">Password:</label>
                        <input className="form-text" type="password" id="signup-password" name="password"
                               required/>
                        <label className="margin-top--sm" htmlFor="signup-password-repeat">Repeat
                            password:</label>
                        <input className="form-text" type="password" id="signup-password-repeat"
                               name="password"
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