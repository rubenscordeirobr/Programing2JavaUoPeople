package uopeople.assignment.unit5.validation;

import uopeople.assignment.unit5.annotation.NotNull;

/**
 * Validator for the {@code @NotNull} annotation.
 * 
 * This class ensures that a field value annotated with {@code @NotNull}
 * is not null, returning a validation result accordingly.
 */
public class NotNullValidator {

    /**
     * Validates whether a field value annotated with {@code @NotNull} is not null.
     *
     * @param annotation The {@code @NotNull} annotation applied to the field.
     * @param value      The value of the field being validated.
     * @return A {@code FieldValidationResult} indicating whether the field passed validation.
     */
    public static FieldValidationResult validate(NotNull annotation, Object value) {
        
        // If the value is null, return a failure result with an appropriate message
        if (value == null) {
            return new FieldValidationResult("Value cannot be null");
        }
        
        // If the value is not null, return a success result
        return FieldValidationResult.success();
    }
}
