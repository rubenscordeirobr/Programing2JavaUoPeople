package uopeople.assignment.unit5.validation;

import uopeople.assignment.unit5.annotation.PrimaryKey;

/**
 * Validator for the {@code @PrimaryKey} annotation.
 * 
 * This class ensures that a field value annotated with {@code @PrimaryKey}
 * adheres to the
 * constraints defined in the annotation, such as being non-null and positive
 * (for numeric types).
 */
public class PrimaryKeyValidator {

    /**
     * Validates a field value annotated with {@code @PrimaryKey}.
     *
     * @param annotation The {@code @PrimaryKey} annotation applied to the field.
     * @param value      The value of the field being validated.
     * @return A {@code FieldValidationResult} indicating whether the field passed
     *         validation.
     *         If {@code annotation.value()} is {@code false}, the field is
     *         considered valid without further checks.
     * @throws IllegalArgumentException if the value is of an unsupported type.
     */
    public static FieldValidationResult validate(PrimaryKey annotation, Object value) {

        // If the primary key is not required (annotation value is false), it's always
        // valid
        if (!annotation.value()) {
            return FieldValidationResult.success();
        }

        // Validate that the value is not null
        if (value == null) {
            return new FieldValidationResult("Value cannot be null");
        }

        // Handle Integer type primary keys
        if (value instanceof Integer) {
            int intValue = (int) value;
            if (intValue <= 0) {
                return new FieldValidationResult("Value must be greater than 0");
            }
            return FieldValidationResult.success();
        }

        // Handle Long type primary keys
        if (value instanceof Long) {
            long longValue = (long) value;
            if (longValue <= 0) {
                return new FieldValidationResult("Value must be greater than 0");
            }
            return FieldValidationResult.success();
        }

        // Handle String type primary keys
        if (value instanceof String) {
            String str = (String) value;
            if (str.isEmpty()) {
                return new FieldValidationResult("Value must not be an empty string");
            }
            return FieldValidationResult.success();
        }

        // Unsupported type
        String typeName = value.getClass().getName();
        throw new IllegalArgumentException("Type " + typeName + " is not supported for PrimaryKey validation");
    }
}
