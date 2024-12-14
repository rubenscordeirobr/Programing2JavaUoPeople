package uopeople.assignment.unit5.models;

import uopeople.assignment.unit5.validation.Validator;
import uopeople.assignment.unit5.validation.ValidationResult;

/**
 * Abstract base class for all models in the application.
 * 
 * Provides a standard method for validating model instances using the Validator class.
 * Subclasses of Model can invoke the {@code validate} method to validate their fields
 * based on defined rules.
 */
public abstract class Model {

    /**
     * Validates the current model instance.
     *
     * This method uses the {@code Validator} class to perform validation checks on the
     * annotated fields of the model and returns a {@code ValidationResult} object containing
     * the results of the validation.
     *
     * @return A {@code ValidationResult} object that indicates whether the model is valid
     *         and contains any validation errors if applicable.
     */
    public ValidationResult validate() {
        // Delegate validation logic to the Validator class
        return Validator.validate(this);
    }
}
