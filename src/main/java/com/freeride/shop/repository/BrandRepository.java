package com.freeride.shop.repository;

import com.freeride.shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);
}
