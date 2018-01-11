package com.freeride.shop.validation;

import com.freeride.shop.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements
        ConstraintValidator<UniqueUsername, String> {

    private UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
//        Class<? extends UserService> serviceClass = uniqueUsername.service();
//        UserService userService = this.applicationContext.getBean(serviceClass);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return userService.getUserByName(username) == null;
    }
}
