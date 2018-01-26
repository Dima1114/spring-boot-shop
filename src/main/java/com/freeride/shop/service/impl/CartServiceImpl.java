package com.freeride.shop.service.impl;

import com.freeride.shop.dto.CartDto;
import com.freeride.shop.entity.Cart;
import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.ItemAvailability;
import com.freeride.shop.entity.User;
import com.freeride.shop.repository.CartRepository;
import com.freeride.shop.repository.ItemAvailabilityRepository;
import com.freeride.shop.service.CartService;
import com.freeride.shop.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private ItemAvailabilityRepository itemAvailabilityRepository;
    private CartRepository cartRepository;
    private SecurityService securityService;

    public CartServiceImpl(ItemAvailabilityRepository itemAvailabilityRepository,
                           CartRepository cartRepository, SecurityService securityService) {
        this.itemAvailabilityRepository = itemAvailabilityRepository;
        this.cartRepository = cartRepository;
        this.securityService = securityService;
    }

    @Override
    public void addItem(CartDto cartDto) {
        ItemAvailability itemAvailability =
                itemAvailabilityRepository.findByItem_IdAndSize_Id(cartDto.getItemId(), cartDto.getSizeId());
        Cart cartItem = new Cart();
        cartItem.setItemAvailability(itemAvailability);
        cartItem.setUser(securityService.getCurrentUser());
        cartRepository.save(cartItem);
    }

    @Override
    public void removeItem(Long availabilityId) {
        removeItem(availabilityId, securityService.getCurrentUser());
    }

    @Override
    public void removeItem(Long availabilityId, User user) {
        //don`t deleting by user and availability because all items with the same
        // availability Id and user will be deleted and I need only ONE
        Cart cartItem = cartRepository.findFirstByUserAndItemAvailability_Id(user, availabilityId);
        cartRepository.delete(cartItem);
    }

    @Override
    public List<ItemAvailability> listAvailabilities() {
        return listAvailabilities(securityService.getCurrentUser());
    }

    @Override
    public List<ItemAvailability> listAvailabilities(User user) {
        return cartRepository.findAllByUser(user).stream()
                .map(Cart::getItemAvailability)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getCartTotal() {
        return getCartTotal(securityService.getCurrentUser());
    }

    @Override
    public BigDecimal getCartTotal(User user) {
        return listAvailabilities(user).stream()
                .map(ItemAvailability::getItem)
                .map(Item::getPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
