import styles from './LabeledInput.module.css';
import React from 'react';

const LabeledInput = (props) => {
    return (
        <div className={styles.labeledInput}>
            <label className={styles.label} htmlFor={props.inputName}>{props.label}</label>
            <input className={styles.input} type={props.type} id={props.inputName} name={props.inputName}
                   onChange={props.onChange}/>
        </div>
    );
};

export default LabeledInput;

