package com.freeride.shop.service.impl;

import com.freeride.shop.dto.CategoryDto;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.repository.CategoryRepository;
import com.freeride.shop.service.CategoryService;
import com.freeride.shop.service.ItemService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public long countCategories() {
        return categoryRepository.count();
    }

    @Override
//    @Cacheable("categories")
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
//    @CacheEvict("categories")
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setSizeType(categoryDto.getSizeType());
        categoryRepository.save(category);
    }

    @Override
//    @CacheEvict("categories")
    public void deleteCategory(Long categoryId) {
        categoryRepository.delete(categoryId);
    }
}
















