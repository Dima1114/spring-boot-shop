package com.freeride.shop.dto;

import com.freeride.shop.entity.Category;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class CategoryDto {

    private Long id;
    @Size(min=3, max=20, message = "{Size.categoryDto.name}")
    private String name;
    private String description;

    @NotEmpty(message = "{NotEmpty.categoryDto.sizeType}")
    private String sizeType;

    public CategoryDto() {
    }

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.sizeType = category.getSizeType();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }
}
