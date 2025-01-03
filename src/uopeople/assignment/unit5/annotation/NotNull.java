package uopeople.assignment.unit5.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD) // Applicable to fields
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for validation
public @interface NotNull {
    String message() default "Field cannot be null"; // Optional error message
}
