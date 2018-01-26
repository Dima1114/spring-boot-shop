package com.freeride.shop.entity;

import com.freeride.shop.listener.BrandEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@EntityListeners(BrandEntityListener.class)
public class Brand extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
