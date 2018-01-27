package com.freeride.shop.service;

import com.freeride.shop.dto.CategoryDto;
import com.freeride.shop.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();
    Category getCategory(String name);
    Category getCategory(Long id);
    void saveCategory(CategoryDto categoryDto);
    long countCategories();

    //admin
    void deleteCategory(Long categoryId);
}
