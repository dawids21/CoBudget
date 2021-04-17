import React from 'react';
import styles from './Form.module.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faAngleLeft} from '@fortawesome/free-solid-svg-icons';

const LogoContainer = () => {
    return (
        <div className={styles.logoContainer}>
            <h1 className={styles.logo}>CoBudget</h1>
        </div>
    );
};

const Form = (props) => {
    return (
        <div className={styles.container}>
            <LogoContainer/>
            <a className={styles.formBackButton} href={props.returnPage}>
                <FontAwesomeIcon icon={faAngleLeft} size="3x"/>
            </a>
            <form className={styles.form} onSubmit={(event) => props.onSubmit(event)}>
                <div className={styles.formData}>
                    {props.children}
                </div>
                <input type="submit" className={styles.formSubmit}
                       value={props.buttonText} disabled={props.buttonDisabled}/>
            </form>
        </div>
    );
};

export default Form;