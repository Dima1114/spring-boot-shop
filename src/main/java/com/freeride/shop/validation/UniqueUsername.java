package com.freeride.shop.validation;

import com.freeride.shop.service.UserService;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Login already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends UserService> service() default UserService.class;
}
