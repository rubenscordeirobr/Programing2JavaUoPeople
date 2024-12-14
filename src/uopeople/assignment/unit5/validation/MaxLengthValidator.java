package uopeople.assignment.unit5.validation;

import uopeople.assignment.unit5.annotation.MaxLength;

public class MaxLengthValidator {

    public static FieldValidationResult validate(MaxLength annotation, Object value) {
        
        if (value == null) {
            return FieldValidationResult.success();
        }
        
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Value must be a string");
        }
        
        String str = (String) value;
        if (str.length() > annotation.value()) {
            return new FieldValidationResult(" String length must be less than or equal to " + annotation.value());
        }
        
        return FieldValidationResult.success();
    }

}
