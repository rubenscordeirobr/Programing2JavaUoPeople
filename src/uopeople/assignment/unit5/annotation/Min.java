package uopeople.assignment.unit5.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD) // Applicable to fields
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime for validation
public @interface Min {

    /**
     * The minimum allowed value.
     */
    int value();

    /**
     * Custom error message.
     * Default: "Value is below the minimum required".
     */
    String message() default "Value is below the minimum required";
}
