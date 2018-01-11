package com.freeride.shop.service;

import com.freeride.shop.dto.BrandDto;
import com.freeride.shop.entity.Brand;

import java.util.List;

public interface BrandService {

    List<Brand> brandsList();

    Brand getBrand(String name);
    Brand getBrandById(Long id);

    void saveBrand(BrandDto brandDto);

    void deleteBrand(Long brandId);
}
