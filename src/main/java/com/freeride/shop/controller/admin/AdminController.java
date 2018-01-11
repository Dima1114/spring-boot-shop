package com.freeride.shop.controller.admin;

import com.freeride.shop.service.CategoryService;
import com.freeride.shop.service.CommentService;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ItemService itemService;
    private CategoryService categoryService;
    private UserService userService;
    private CommentService commentService;

    public AdminController(ItemService itemService, CategoryService categoryService, UserService userService, CommentService commentService) {
        this.itemService = itemService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("itemsOnSite", itemService.countItems());
        model.addAttribute("categoriesOnSite", categoryService.countCategories());
        model.addAttribute("usersOnSite", userService.countUsers());
        model.addAttribute("comments", commentService.getRecentlyAdded());
        return "pages/admin-page";
    }
}
