package com.freeride.shop.dto;

import com.freeride.shop.entity.ItemAvailability;

public class ItemAvailabilityDto {

    private Long id;
    private String size;
    private Integer quantity;

    public ItemAvailabilityDto() {
    }

    public ItemAvailabilityDto(ItemAvailability itemAvailability) {
        this.id = itemAvailability.getId();
        this.size = itemAvailability.getSize().getSize();
        this.quantity = itemAvailability.getQuantity();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
