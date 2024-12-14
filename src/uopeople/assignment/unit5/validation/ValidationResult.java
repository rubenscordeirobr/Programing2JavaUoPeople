package uopeople.assignment.unit5.validation;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents the result of a validation process, aggregating individual field validation results.
 * It provides methods to check if all fields are valid, retrieve validation errors, and extract error messages.
 */
public class ValidationResult {

    // Array of validation errors for the fields that failed validation
    private FieldValidationResult[] validationErrors;

    /**
     * Constructs a ValidationResult object by filtering out only the invalid field validation results.
     *
     * @param errors A list of FieldValidationResult objects containing validation results for various fields.
     */
    public ValidationResult(ArrayList<FieldValidationResult> errors) {
        // Filter and store only invalid validation results
        this.validationErrors = errors.stream()
                .filter(error -> !error.isValid())
                .toArray(FieldValidationResult[]::new);
    }

    /**
     * Checks if all fields passed validation.
     *
     * @return true if there are no validation errors; false otherwise.
     */
    public boolean isValid() {
        return this.validationErrors.length == 0;
    }

    /**
     * Retrieves all validation errors as an array of FieldValidationResult objects.
     *
     * @return An array of FieldValidationResult objects representing validation errors.
     */
    public FieldValidationResult[] getValidationErrors() {
        return this.validationErrors;
    }

    /**
     * Extracts the error messages from all validation errors.
     *
     * @return An array of error messages from invalid fields.
     */
    public String[] getMessages() {
        // Map the validation errors to their messages
        return Arrays.stream(this.validationErrors)
                .map(FieldValidationResult::getMessage)
                .toArray(String[]::new);
    }
}
