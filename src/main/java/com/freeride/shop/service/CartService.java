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
    BigDecimal getCartTotal();
    List<ItemAvailability> listAvailabilities(User user);
    BigDecimal getCartTotal(User user);

    //admin
    void removeItem(Long availId, User user);

}
