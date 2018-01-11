package com.freeride.shop.dto;

import com.freeride.shop.entity.Brand;

import javax.validation.constraints.Size;

public class BrandDto {

    private Long id;

    @Size(min=1, max=20, message = "{Size.brandDto.name}")
    private String name;

    public BrandDto() {
    }

    public BrandDto(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


