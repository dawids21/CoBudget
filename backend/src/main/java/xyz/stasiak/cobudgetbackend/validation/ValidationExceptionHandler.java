package xyz.stasiak.cobudgetbackend.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = ValidationExceptionProcessing.class)
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var validationErrors = new ValidationErrors();
        ex.getBindingResult()
          .getAllErrors()
          .forEach((error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              validationErrors.addError(fieldName, errorMessage);
          });
        return ResponseEntity.badRequest()
                             .body(validationErrors);
    }
}
