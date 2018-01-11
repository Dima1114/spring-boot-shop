package com.freeride.shop.controller;

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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class IndexControllerTest {

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
        mockMvc = MockMvcBuilders.standaloneSetup(new IndexController(itemService))
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
    public void indexShouldContainItemsAndReturnViewName() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/main-page"))
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attributeExists("brands"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("sizes"));
    }

    @Test
    public void aboutShouldContainTitleAndReturnViewName() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/secondary-page"))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attribute("title", "About"))
                .andExpect(model().attributeExists("brands"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attributeExists("sizes"));
    }


}
