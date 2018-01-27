package com.freeride.shop.controller;

import com.freeride.shop.dto.RegistrationDto;
import com.freeride.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping("/login")
    public String login(Model model, String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid login or password");
        }
        return "pages/login";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationDto());
        return "pages/registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("registrationForm") @Valid RegistrationDto registrationDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/registration";
        }
        userService.registerUser(registrationDto);
        return "redirect:/login";
    }
}
