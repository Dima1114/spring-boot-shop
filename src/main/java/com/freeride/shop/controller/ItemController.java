package com.freeride.shop.controller;

import com.freeride.shop.dto.SortDto;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.service.CategoryService;
import com.freeride.shop.service.CommentService;
import com.freeride.shop.service.ItemAvailabilityService;
import com.freeride.shop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/items")
public class ItemController {

    private CategoryService categoryService;
    private ItemService itemService;
    private ItemAvailabilityService itemAvailabilityService;
    private CommentService commentService;

    public ItemController(CategoryService categoryService, ItemService itemService, ItemAvailabilityService itemAvailabilityService, CommentService commentService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.itemAvailabilityService = itemAvailabilityService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public String showProducts(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Item> items = itemService.list(page);

        model.addAttribute("sortForm", new SortDto());
        model.addAttribute("items", items.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", items.getTotalPages());
        return "pages/shop-page";
    }

    @GetMapping("/{categoryName}")
    public String showCategoryProducts(@PathVariable String categoryName, Model model,
                                       @RequestParam(name = "page", defaultValue = "1") int page) {
        Category currentCategory = categoryService.getCategory(categoryName);
        Page<Item> items = itemService.listCategoryItems(currentCategory.getId(), page);

        model.addAttribute("sortForm", new SortDto());
        model.addAttribute("currentCategory", currentCategory.getName());
        model.addAttribute("items", items.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", items.getTotalPages());
        return "pages/shop-page";
    }

    @GetMapping("/show/{itemId}")
    public String showSingleItem(Model model, @PathVariable Long itemId) {
        Item item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        model.addAttribute("itemSizes", itemAvailabilityService.getItemSizesWithPositiveQuantity(item));
        model.addAttribute("itemComments", commentService.getItemsComments(item));
        return "pages/item-page";
    }

    @GetMapping("/{itemId}/image")
    public ResponseEntity<byte[]> fetchImage(@PathVariable Long itemId) {

        return ResponseEntity.ok(itemService.getImage(itemId));
    }

}
