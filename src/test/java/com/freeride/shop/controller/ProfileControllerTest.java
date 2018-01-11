package com.freeride.shop.controller;

import com.freeride.shop.ShopApplication;
import com.freeride.shop.entity.User;
import com.freeride.shop.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ShopApplication.class)
public class ProfileControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserService userService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        User user = new User();
        user.setId(1L);
        user.setUsername("foo");
        user.setPassword("bar");
        userRepository.save(user);
    }

    @Test
    public void updateProfileSuccess() throws Exception {
        mockMvc.perform(post("/profile/update/1")
                .param("id", "1")
                .param("firstName", "Hello")
                .param("lastName", "World")
                .param("password", "bar")
                .param("email", "mail@mail.com")
                .param("newPassword", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("user"));
    }

    @Test
    public void updateProfileFailWrongPassword() throws Exception {
        mockMvc.perform(post("/profile/update/1")
                .param("id", "1")
                .param("firstName", "Hello")
                .param("lastName", "World")
                .param("password", "b")
                .param("email", "mail@mail.com")
                .param("newPassword", "password123"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(model().attributeExists("allRoles"))
                .andExpect(model().attributeExists("cartAvailabilities"))
                .andExpect(model().attributeExists("cartTotal"));
    }

    @Test
    public void updateProfileFailEmptyPassword() throws Exception {
        mockMvc.perform(post("/profile/update/1")
                .param("id", "1")
                .param("firstName", "Hello")
                .param("lastName", "World")
                .param("password", "")
                .param("email", "mail@mail.com")
                .param("newPassword", "password123"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(model().attributeExists("allRoles"))
                .andExpect(model().attributeExists("cartAvailabilities"))
                .andExpect(model().attributeExists("cartTotal"));
    }

    @Test
    public void updateProfileFailEmptyNewPassword() throws Exception {
        mockMvc.perform(post("/profile/update/1")
                .param("id", "1")
                .param("firstName", "Hello")
                .param("lastName", "World")
                .param("password", "bar")
                .param("email", "mail@mail.com")
                .param("newPassword", ""))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(model().attributeExists("allRoles"))
                .andExpect(model().attributeExists("cartAvailabilities"))
                .andExpect(model().attributeExists("cartTotal"));
    }

    @Test
    public void updateProfileFaiInvalidNewPassword() throws Exception {
        mockMvc.perform(post("/profile/update/1")
                .param("id", "1")
                .param("firstName", "Hello")
                .param("lastName", "World")
                .param("password", "bar")
                .param("email", "mail@mail.com")
                .param("newPassword", "p1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("userForm"))
                .andExpect(model().attributeExists("allRoles"))
                .andExpect(model().attributeExists("cartAvailabilities"))
                .andExpect(model().attributeExists("cartTotal"));
    }
}
