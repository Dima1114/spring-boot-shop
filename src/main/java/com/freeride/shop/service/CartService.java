package com.freeride.shop.service;

import com.freeride.shop.dto.CartDto;
import com.freeride.shop.entity.ItemAvailability;
import com.freeride.shop.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void addItem(CartDto cartDto);
    void removeItem(Long availId);
    List<ItemAvailability> listAvailabilities();

    //    List<Item> listCartItems();
    BigDecimal getCartTotal();

    //admin
    void removeItem(Long availId, User user);
    List<ItemAvailability> listAvailabilities(User user);

    //    List<Item> listCartItems(User user);
    BigDecimal getCartTotal(User user);

}
