package xyz.stasiak.cobudgetbackend.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }

        try {
            var dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
                                                     .withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(object, dateTimeFormatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}