package ir.irancelllabs.hr.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalIdValidator
        implements ConstraintValidator<NationalId, String> {

    @Override
    public boolean isValid(
            String value,
            ConstraintValidatorContext context) {

        return validateNationalId(value);
    }

    private boolean validateNationalId(String nationalId) {

        if (nationalId == null) {
            return false;
        }

        // Must contain exactly 10 digits
        if (!nationalId.matches("\\d{10}")) {
            return false;
        }

        // Cannot contain the same digit
        if (nationalId.chars().distinct().count() == 1) {
            return false;
        }

        return true;
    }
}