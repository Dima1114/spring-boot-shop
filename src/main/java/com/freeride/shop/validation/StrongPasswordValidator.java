package com.freeride.shop.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements
        ConstraintValidator<StrongPassword, String> {

    private boolean nullable;
    private int min;
    private int max;

    @Override
    public void initialize(StrongPassword strongPassword) {
        this.nullable = strongPassword.nullanle();
        this.min = strongPassword.min();
        this.max = strongPassword.max();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (nullable && (password == null || password.trim().isEmpty())) {
            return true;
        } else if (password == null) {
            return false;
        }
        String regex = "\\w{" + min + "," + max + "}";
        return (password.matches("(.*\\d.*[a-zA-Z].*)|(.*[a-zA-Z].*\\d.*)") && password.matches(regex));
    }
}
