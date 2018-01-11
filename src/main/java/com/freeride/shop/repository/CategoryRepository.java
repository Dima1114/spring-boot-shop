package com.freeride.shop.repository;

import com.freeride.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);
    Category findById(Long id);
}
