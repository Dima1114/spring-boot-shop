package com.freeride.shop.controller;

import com.freeride.shop.dto.SortDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/items/all/search")
public class SearchController {

    @PostMapping
    public String sortItems(@ModelAttribute("sortForm") SortDto sortDto, Model model, HttpSession session,
                            @RequestParam(name = "page", defaultValue = "1") int page) {
        session.setAttribute("sortForm", sortDto);
        model.addAttribute("sortForm", sortDto);

        return "pages/shop-page";
    }

    @GetMapping
    public String nextPageSortedItems(Model model, HttpSession session,
                                      @RequestParam(name = "page", defaultValue = "1") int page) {

        SortDto sortDto = ((SortDto) session.getAttribute("sortForm"));
        model.addAttribute("sortForm", sortDto);

        return "pages/shop-page";
    }
}
