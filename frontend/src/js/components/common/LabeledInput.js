import React from 'react';
import styles from './LabeledInput.module.css';

const LabeledInput = (props) => {
    return (
        <div className={styles.labeledInput}>
            <label className={styles.labeledInputLabel} htmlFor={props.inputName}>{props.label}</label>
            <input className={styles.labeledInputText} type={props.type} id={props.inputName} name={props.inputName}
                   onChange={props.onChange} required={props.required}/>
        </div>
    );
};

export default LabeledInput;

