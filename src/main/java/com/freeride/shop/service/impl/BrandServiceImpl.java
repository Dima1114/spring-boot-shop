package com.freeride.shop.service.impl;

import com.freeride.shop.dto.BrandDto;
import com.freeride.shop.entity.Brand;
import com.freeride.shop.repository.BrandRepository;
import com.freeride.shop.service.BrandService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    private CacheManager cacheManager;

    public BrandServiceImpl(BrandRepository brandRepository, CacheManager cacheManager) {
        this.brandRepository = brandRepository;
        this.cacheManager = cacheManager;
    }

    @Override
    public Brand getBrand(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findOne(id);
    }

    @Override
    public void saveBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setId(brandDto.getId());
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        cacheManager.getCache("brands").clear();
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandRepository.delete(brandId);
        cacheManager.getCache("brands").clear();
    }

    @Override
    @Cacheable("brands")
    public List<Brand> brandsList() {
        return brandRepository.findAll();
    }
}
