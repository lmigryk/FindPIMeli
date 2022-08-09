package com.example.meli.commons.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RandomValidatorDecorator.class)
@Target( {ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RandomConstraint {

    String message() default "Random parameter would cause overflow";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}



