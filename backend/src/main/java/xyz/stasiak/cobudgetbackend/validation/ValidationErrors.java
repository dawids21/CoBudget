package xyz.stasiak.cobudgetbackend.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationErrors {

    private final Map<String, String> errors;

    public ValidationErrors() {
        this.errors = new HashMap<>();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }
}
