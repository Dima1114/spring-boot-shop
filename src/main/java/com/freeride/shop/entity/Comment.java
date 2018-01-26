package com.freeride.shop.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    @Column(name = "date")
    private LocalDateTime addingDate = LocalDateTime.now();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDateTime getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(LocalDateTime addingDate) {
        this.addingDate = addingDate;
    }
}
