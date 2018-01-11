package com.freeride.shop.interseptors;

import com.freeride.shop.entity.Brand;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Size;
import com.freeride.shop.service.BrandService;
import com.freeride.shop.service.CategoryService;
import com.freeride.shop.service.SizeService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class ItemsInterceptor extends HandlerInterceptorAdapter {


    private CategoryService categoryService;
    private BrandService brandService;
    private SizeService sizeService;

    public ItemsInterceptor(CategoryService categoryService, BrandService brandService, SizeService sizeService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.sizeService = sizeService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Category> categories = categoryService.list();
        List<Brand> brands = brandService.brandsList();
        List<Size> sizes = sizeService.getSizes();

        modelAndView.addObject("brands", brands);
        modelAndView.addObject("sizes", sizes);
        modelAndView.addObject("categories", categories);
    }
}
