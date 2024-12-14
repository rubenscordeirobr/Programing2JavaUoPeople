package uopeople.assignment.unit5.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD) // Applicable to fields
@Retention(RetentionPolicy.RUNTIME) // Retained at runtime
public @interface ForeignKey {

    boolean value() default true; // Indicates whether the foreign key value is required
    
    String message() default "Invalid foreign key reference";
}
