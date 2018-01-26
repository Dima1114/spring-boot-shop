package com.freeride.shop.entity;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private ItemAvailability itemAvailability;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ItemAvailability getItemAvailability() {
        return itemAvailability;
    }

    public void setItemAvailability(ItemAvailability itemAvailability) {
        this.itemAvailability = itemAvailability;
    }
}
