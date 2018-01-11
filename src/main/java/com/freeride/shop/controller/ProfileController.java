package com.freeride.shop.controller;

import com.freeride.shop.dto.ProfileDto;
import com.freeride.shop.entity.User;
import com.freeride.shop.service.CartService;
import com.freeride.shop.service.RoleService;
import com.freeride.shop.service.SecurityService;
import com.freeride.shop.service.UserService;
import com.freeride.shop.validation.ProfileUpdateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private SecurityService securityService;
    private CartService cartService;
    private UserService userService;
    private RoleService roleService;
    private ProfileUpdateValidator profileUpdateValidator;

    public ProfileController(SecurityService securityService, CartService cartService, UserService userService, RoleService roleService, ProfileUpdateValidator profileUpdateValidator) {
        this.securityService = securityService;
        this.cartService = cartService;
        this.userService = userService;
        this.roleService = roleService;
        this.profileUpdateValidator = profileUpdateValidator;
    }

//    @InitBinder
//    public void addRegistrationValidator(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(profileUpdateValidator);
//    }

    @GetMapping
    public String profile(Model model) {

        User user = Optional.ofNullable((User) model.asMap().remove("user"))
                .orElse(securityService.getCurrentUser());

        model.addAttribute("userForm", new ProfileDto(user));
        addToProfile(model, user);
        return "pages/profile-page";
    }

    @PostMapping("/update/{userId}")
    public String updateProfile(Model model, RedirectAttributes redirectAttributes,
                                @ModelAttribute("userForm") @Valid ProfileDto profileDto,
                                BindingResult bindingResult, @PathVariable Long userId) {
        profileUpdateValidator.validate(profileDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", profileDto);
            addToProfile(model, userService.getUser(userId));
            return "pages/profile-page";
        } else {
            userService.updateUser(userId, profileDto);
            redirectAttributes.addFlashAttribute("user", userService.getUser(userId));
            return "redirect:/profile";
        }
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> fetchImage(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.image(userId));
    }

    private void addToProfile(Model model, User user) {
        model.addAttribute("allRoles", roleService.getRoles());
        model.addAttribute("cartAvailabilities", cartService.listAvailabilities(user));
        model.addAttribute("cartTotal", cartService.getCartTotal(user));
    }
}
