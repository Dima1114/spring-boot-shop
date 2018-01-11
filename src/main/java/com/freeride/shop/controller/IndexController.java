package com.freeride.shop.controller;

import com.freeride.shop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    private ItemService itemService;

    public IndexController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("items", itemService.listMostRated());
        return "pages/main-page";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "pages/secondary-page";
    }
}
