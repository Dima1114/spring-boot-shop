package com.freeride.shop.repository;

import com.freeride.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByCategory_Id(Long categoryId, Pageable pageable);
    List<Item> findTop6ByOrderByRatingDesc();
    Page<Item> findAllByIdInAndCategory_NameContainingAndNameContainingAndBrand_NameInAndPriceBetween(
           List<Long> itemIds, String category, String name, List<String> brands,
           BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    Page<Item> findAllByName(String name, Pageable pageable);
    List<Item> findAllByCategory_Id(Long categoryId);
    List<Item> findAllByBrand_Id(Long brandId);
}
