package uopeople.assignment.unit5.validation;

/**
 * Represents the result of a field validation operation.
 * This class holds the validation status (valid/invalid)
 * and an associated message explaining the result.
 */
public class FieldValidationResult {

    // A message describing the validation result (e.g., error details or success information)
    private String message;

    // Indicates whether the field is valid or not
    private boolean isValid;

    /**
     * Constructs a FieldValidationResult with a specified message and validity status.
     *
     * @param message A message describing the validation result.
     * @param isValid A boolean indicating whether the field is valid.
     */
    public FieldValidationResult(String message) {
        this.message = message;
        this.isValid = false;
    }

    // Private constructor for creating a successful validation result
    private FieldValidationResult() {
        this.message = null;
        this.isValid = true;
    }
 
    /**
     * Creates a new FieldValidationResult object with the specified message.
     *
     * @param message A message describing the validation result.
     * @return A new FieldValidationResult object representing a failed validation.
     */
    public static FieldValidationResult  success() {
        return new FieldValidationResult();
    }

   
    /**
     * Returns whether the field passed validation.
     *
     * @return true if the field is valid; false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Returns the message describing the validation result.
     *
     * @return The validation result message.
     */
    public String getMessage() {
        return message;
    }
}
