package uopeople.assignment.unit5.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD) // Applicable to fields
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for validation
public @interface MaxLength {
    int value(); // Maximum length value
    String message() default "Field exceeds maximum length"; // Optional error message
}
