package uopeople.assignment.unit5.validation;

import uopeople.assignment.unit5.annotation.Min;

/**
 * Validator for the {@code @Min} annotation.
 * 
 * This class ensures that a field value annotated with {@code @Min}
 * meets the minimum numerical value constraint.
 */
public class MinValidator {

    /**
     * Validates whether a field value annotated with {@code @Min} meets the
     * minimum value requirement.
     *
     * @param annotation The {@code @Min} annotation applied to the field.
     * @param value      The value of the field being validated.
     * @return A {@code FieldValidationResult} indicating whether the field passed validation.
     *         If the value is null, validation passes by default.
     * @throws IllegalArgumentException if the value is not a {@code Number}.
     */
    public static FieldValidationResult validate(Min annotation, Object value) {
        
        // If the value is null, treat it as valid since @Min does not handle null checks
        if (value == null) {
            return FieldValidationResult.success();
        }

        // Ensure the value is a Number
        if (!(value instanceof Number)) {
            throw new IllegalArgumentException("Value must be a number");
        }

        // Cast the value to a Number and compare it with the minimum value from the annotation
        Number number = (Number) value;
        if (number.doubleValue() < annotation.value()) {
            return new FieldValidationResult(
                "Value must be greater than or equal to " + annotation.value());
        }

        // Return success if the value meets the minimum requirement
        return FieldValidationResult.success();
    }
}
