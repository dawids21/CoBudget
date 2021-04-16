import React from 'react';

const Logo = () => {
    return <h1 className="logo disable-select">CoBudget</h1>;
};

const Form = (props) => {
    return (
        <div className="container-column">
            <div className="logo-container">
                <Logo/>
            </div>
            <a id="back-button" className="icon-button" href={props.returnPage}><i
                className="fas fa-angle-left fa-3x"/></a>
            <form className="form" onSubmit={(event) => props.onSubmit(event)}>
                <div className="form-data">
                    {props.children}
                </div>
                <input type="submit" className="button disable-select"
                       value={props.buttonText} disabled={props.buttonDisabled}/>
            </form>
        </div>
    );
};

export default Form;