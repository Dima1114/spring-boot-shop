package com.freeride.shop.controller.admin;

import com.freeride.shop.dto.ItemDto;
import com.freeride.shop.entity.Item;
import com.freeride.shop.service.ItemService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("admin/items")
public class AdminItemsController {

    private ItemService itemService;

    public AdminItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, orderDateEditor);
    }

    @GetMapping
    public String showItems(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<Item> items = itemService.list(page);

        model.addAttribute("items", items.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPages", items.getTotalPages());
        return "pages/admin-page";
    }

    @GetMapping("/filter")
    public String showFilteredItems(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "name", defaultValue = "") String name) {

        Page<Item> items = itemService.adminSearchResults(name, page);

        model.addAttribute("items", items.getContent());
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", items.getTotalPages());
        return "pages/admin-page";
    }

    @GetMapping("/edit/{itemId}")
    public String itemEditForm(Model model, @PathVariable Long itemId) {
        Item item = itemService.getItem(itemId);
        ItemDto itemDto = new ItemDto(item);
        model.addAttribute("itemForm", itemDto);
        return "pages/admin-page";
    }

    @GetMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return "redirect:/admin/items";
    }

    @GetMapping("/add")
    public String addItem(Model model) {
        model.addAttribute("itemForm", new ItemDto());
        return "pages/admin-page";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute("itemForm") @Valid ItemDto itemDto,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/admin-page";
        }
        itemService.saveItem(itemDto);
        return "redirect:/admin/items";
    }
}
