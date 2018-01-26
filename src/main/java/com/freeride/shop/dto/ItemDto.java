package com.freeride.shop.dto;

import com.freeride.shop.entity.Item;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ItemDto {

    private Long id;

    @Size(min = 3, max = 100, message = "{Size.itemDto.name}")
    private String name = "";

    @Size(min = 3, max = 1000, message = "{Size.itemDto.description}")
    private String description = "";

    @Range(min = 1, max = 1000, message = "{Size.itemDto.price}")
    private BigDecimal price = new BigDecimal(0);

    @NotEmpty(message = "{NotEmpty.itemDto.brand}")
    private String brand = "";

    @NotEmpty(message = "{NotEmpty.itemDto.category}")
    private String category = "";

    private Date arrivalDate = new Date();

    private Map<String, Integer> sizeQuantity;

    private MultipartFile image;

    public ItemDto() {
    }

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice();
        if(item.getBrand() != null){
            this.brand = item.getBrand().getName();
        }
        if(item.getCategory() != null){
            this.category = item.getCategory().getName();
        }
        this.arrivalDate = Timestamp.valueOf(item.getArrivalDate());
        this.sizeQuantity = new HashMap<>();
        item.getItemAvailabilities().forEach(ia -> sizeQuantity.put(ia.getSize().getSize(), ia.getQuantity()));
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Map<String, Integer> getSizeQuantity() {
        return sizeQuantity;
    }

    public void setSizeQuantity(Map<String, Integer> sizeQuantity) {
        this.sizeQuantity = sizeQuantity;
    }

}
