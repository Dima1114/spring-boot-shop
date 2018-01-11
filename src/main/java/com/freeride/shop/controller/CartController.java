package com.freeride.shop.controller;

import com.freeride.shop.dto.CartDto;
import com.freeride.shop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addToCart(@RequestBody CartDto cartDto) {
        cartService.addItem(cartDto);
    }

    @DeleteMapping(path = "/remove/{availabilityId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void removeFromCart(@PathVariable Long availabilityId) {
        cartService.removeItem(availabilityId);
    }
}
