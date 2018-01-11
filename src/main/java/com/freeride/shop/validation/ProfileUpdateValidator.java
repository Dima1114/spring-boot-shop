package com.freeride.shop.validation;

import com.freeride.shop.dto.ProfileDto;
import com.freeride.shop.entity.User;
import com.freeride.shop.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileUpdateValidator implements Validator {

    private UserService userService;

    public ProfileUpdateValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProfileDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfileDto profileDto = ProfileDto.class.cast(target);
        String newPassword = profileDto.getNewPassword();
        String password = profileDto.getPassword();

        User user = userService.getUser(profileDto.getId());

        if (!password.trim().isEmpty()) {
            if (newPassword.trim().isEmpty()) {
                errors.rejectValue("newPassword", "new.password.empty");
            } else if (!password.equals(user.getPassword())) {
                errors.rejectValue("password", "password.wrong");
            }
        } else if (password.trim().isEmpty() && !newPassword.trim().isEmpty()) {
            errors.rejectValue("password", "password.empty");
        }
    }
}
