package com.freeride.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freeride.shop.dto.CartDto;
import com.freeride.shop.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
@ContextConfiguration(classes = {CartController.class})
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CartService cartService;

    @Test
    @WithMockUser(username = "user", password = "123")
    public void addingItemToCart() throws Exception {
        CartDto cartDto = new CartDto();
        cartDto.setItemId(1L);
        cartDto.setSizeId(10L);

        mockMvc.perform(post("/cart/add").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isOk());
        verify(cartService, times(1)).addItem(any());

    }

    @Test
    @WithMockUser(username = "user", password = "123")
    public void deletingItemFromCart() throws Exception {
        mockMvc.perform(delete("/cart//remove/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        verify(cartService, times(1)).removeItem(any());
    }
}
