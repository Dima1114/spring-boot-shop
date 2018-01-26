package com.freeride.shop.controller.admin;

import com.freeride.shop.dto.CategoryDto;
import com.freeride.shop.entity.Category;
import com.freeride.shop.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

    private CategoryService categoryService;

    public AdminCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showCategories(Model model) {
        model.addAttribute("allCategories","");
        return "pages/admin-page";
    }

    @GetMapping("/edit/{categoryId}")
    public String categoryEditForm(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.getCategory(categoryId);
        CategoryDto categoryDto = new CategoryDto(category);
        model.addAttribute("categoryForm", categoryDto);
        model.addAttribute("currentCategory", category.getName());

        return "pages/admin-page";
    }

    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return "redirect:/admin/categories";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("categoryForm", new CategoryDto());
        return "pages/admin-page";
    }

    @PostMapping("/add")
    public String addOrUpdateCategory(@ModelAttribute("categoryForm") @Valid CategoryDto categoryDto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/admin-page";
        }
        categoryService.saveCategory(categoryDto);
        return "redirect:/admin/categories";
    }
}
