package uopeople.assignment.unit5.validation;

import uopeople.assignment.unit5.annotation.*;
import uopeople.assignment.unit5.models.Model;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.lang.annotation.Annotation;

/**
 * The Validator class provides validation functionality for models
 * that extend the {@code Model} class. It validates annotated fields
 * in a model instance against specified constraints.
 */
public class Validator {

    /**
     * Validates a model instance by checking its annotated fields against
     * specified validation annotations.
     *
     * @param model The model instance to validate. Must extend {@code Model}.
     * @return A {@code ValidationResult} object containing the results of the validation.
     * @throws IllegalArgumentException if the model is {@code null}.
     * @throws RuntimeException         if a field cannot be accessed.
     */
    public static ValidationResult validate(Model model) {

        // Ensure the model is not null
        if (model == null) {
            throw new IllegalArgumentException("Model is null");
        }

        // Get the class of the model
        Class<?> _class = model.getClass();

        // Create a list to store validation results
        ArrayList<FieldValidationResult> validations = new ArrayList<>();

        // Iterate over all declared fields in the model class
        for (Field field : _class.getDeclaredFields()) {

            field.setAccessible(true); // Allow access to private fields

            // Get all annotations applied to the field
            Annotation[] annotations = field.getAnnotations();

            if (annotations.length > 0) {
                Object value;

                // Retrieve the field's value from the model instance
                try {
                    value = field.get(model);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field value", e);
                }

                // Iterate over all annotations on the field
                for (Annotation annotation : annotations) {

                    // Validate based on the type of annotation
                    if (annotation instanceof PrimaryKey) {
                        validations.add(PrimaryKeyValidator.validate((PrimaryKey) annotation, value));
                    }

                    if (annotation instanceof NotNull) {
                        validations.add(NotNullValidator.validate((NotNull) annotation, value));
                    }

                    if (annotation instanceof MaxLength) {
                        validations.add(MaxLengthValidator.validate((MaxLength) annotation, value));
                    }
                }
            }
        }

        // Return the validation results
        return new ValidationResult(validations);
    }
}
