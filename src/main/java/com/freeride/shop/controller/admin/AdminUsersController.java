package com.freeride.shop.controller.admin;

import com.freeride.shop.dto.RegistrationDto;
import com.freeride.shop.entity.User;
import com.freeride.shop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    private UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.image(userId));
    }

    @GetMapping
    public String showUsers(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<User> users = userService.list(page);

        model.addAttribute("users", users.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", users.getTotalPages());
        return "pages/admin-page";
    }

    @GetMapping("/edit/{userId}")
    public String userEditForm(RedirectAttributes redirectAttributes, @PathVariable Long userId) {
        User user = userService.getUser(userId);
        redirectAttributes.addFlashAttribute("user", user);

        return "redirect:/profile";
    }

    @GetMapping("/delete/{userId}")
    public String deleteItem(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "redirect:/admin/users";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("registrationForm", new RegistrationDto());
        return "pages/admin-page";
    }

}
