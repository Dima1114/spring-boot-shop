package com.freeride.shop.controller;

import com.freeride.shop.dto.RegistrationDto;
import com.freeride.shop.interseptors.CategoryInterceptor;
import com.freeride.shop.interseptors.ItemsInterceptor;
import com.freeride.shop.interseptors.SearchInterceptor;
import com.freeride.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private ItemsInterceptor itemsInterceptor;
    @MockBean
    private SearchInterceptor searchInterceptor;
    @MockBean
    private CategoryInterceptor categoryInterceptor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLoginShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/login"));
    }

    @Test
    public void get403ShouldReturnView() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/403"));
    }

    @Test
    public void getRegistrationForm() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/registration"))
                .andExpect(model().attributeExists("registrationForm"))
                .andExpect(model().attribute("registrationForm", new RegistrationDto()));
    }

    @Test
    public void postRegistrationFails() throws Exception {
        mockMvc.perform(post("/registration")
                .param("login", "1")
                .param("password", "")
                .param("email", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/registration"))
                .andExpect(model().errorCount(3));
    }

    @Test
    public void postRegistrationSuccess() throws Exception {
        mockMvc.perform(post("/registration")
                .param("login", "foofoo")
                .param("password", "bar123")
                .param("email", "email@mail.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

}
