package com.freeride.shop.controller;

import com.freeride.shop.controller.admin.AdminBrandController;
import com.freeride.shop.entity.Brand;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.Size;
import com.freeride.shop.interseptors.ItemsInterceptor;
import com.freeride.shop.service.BrandService;
import com.freeride.shop.service.CategoryService;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.service.SizeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class AdminBrandsControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private BrandService brandService;
    @MockBean
    private SizeService sizeService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminBrandController(brandService))
                .addInterceptors(new ItemsInterceptor(categoryService, brandService, sizeService))
                .build();
        List<Item> items = Arrays.asList(new Item(), new Item());
        List<Category> categories = Arrays.asList(new Category(), new Category());
        List<Brand> brands = Arrays.asList(new Brand(), new Brand());
        List<Size> sizes = Arrays.asList(new Size(), new Size());
        when(itemService.listMostRated()).thenReturn(items);
        when(categoryService.list()).thenReturn(categories);
        when(brandService.brandsList()).thenReturn(brands);
        when(sizeService.getSizes()).thenReturn(sizes);
    }

    @Test
    public void getItemForm() throws Exception {
        mockMvc.perform(get("/admin/brands/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/brands"))
                .andExpect(flash().attributeExists("brandForm"))
                .andExpect(model().attributeExists("brands", "categories", "sizes"));
    }

    @Test
    public void postItemFormHasErrors() throws Exception {
        mockMvc.perform(post("/admin/brands/add")
                .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/admin-page"))
                .andExpect(model().errorCount(1));
    }

    @Test
    public void postItemFormSuccess() throws Exception {
        mockMvc.perform(post("/admin/brands/add")
                .param("name", "name"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/brands"));
        verify(brandService, times(1)).saveBrand(anyObject());
    }
}
