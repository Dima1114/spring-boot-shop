package com.freeride.shop.controller.admin;

import com.freeride.shop.dto.BrandDto;
import com.freeride.shop.entity.Brand;
import com.freeride.shop.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/brands")
public class AdminBrandController {

    private BrandService brandService;

    public AdminBrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String showBrands(Model model) {
        model.addAttribute("allBrands", brandService.brandsList());
        return "pages/admin-page";
    }

    @GetMapping("/edit/{brandId}")
    public String categoryEditForm(@PathVariable Long brandId, RedirectAttributes redirectAttrs) {
        Brand brand = brandService.getBrandById(brandId);
        BrandDto brandDto = new BrandDto(brand);
        redirectAttrs.addFlashAttribute("brandForm", brandDto);
        redirectAttrs.addFlashAttribute("currentBrand", brand.getName());

        return "redirect:/admin/brands";
    }

    @GetMapping("/delete/{brandId}")
    public String deleteCategory(@PathVariable Long brandId){
        brandService.deleteBrand(brandId);
        return "redirect:/admin/brands";
    }

    @GetMapping("/add")
    public String addCategory(RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("brandForm", new BrandDto());
        return "redirect:/admin/brands";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("brandForm") @Valid BrandDto brandDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/admin-page";
        }
        brandService.saveBrand(brandDto);
        return "redirect:/admin/brands";
    }


}
