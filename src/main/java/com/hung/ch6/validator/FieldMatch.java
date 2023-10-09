package com.hung.ch6.validator;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
	String message() default "first和second必須相等";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String first();
    String second();
}
