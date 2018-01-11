package com.freeride.shop.service.impl;

import com.freeride.shop.entity.Size;
import com.freeride.shop.repository.SizeRepository;
import com.freeride.shop.service.SizeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SizesServiceImpl implements SizeService {

    private SizeRepository sizeRepository;

    public SizesServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Size getSize(String name) {
        return sizeRepository.findBySize(name);
    }

    @Override
    @Cacheable("sizes")
    public List<Size> getSizes() {
        return sizeRepository.findAll();
    }

    @Override
    @Cacheable("sizeTypes")
    public List<String> getTypes() {
        return sizeRepository.findAllTypes();
    }

    @Override
    public List<Size> getTypeSizes(String type) {
        return sizeRepository.findAllByType(type);
    }
}
