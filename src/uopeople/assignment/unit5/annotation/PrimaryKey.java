package uopeople.assignment.unit5.annotation;

import java.lang.annotation.*;
 
@Target(ElementType.FIELD) // Applicable to fields
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime
public @interface PrimaryKey {

    /**
     * Indicates whether the primary key value is required.
     * Default: false (auto-incremented primary key).
     */
    boolean value() default false;

    String message() default "Primary key value is missing or invalid";
}
 