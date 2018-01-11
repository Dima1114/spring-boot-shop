package com.freeride.shop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "StrongPassword.validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean nullanle() default false;

    int min();

    int max();
}
