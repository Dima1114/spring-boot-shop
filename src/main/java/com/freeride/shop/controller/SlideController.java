package com.freeride.shop.controller;

import com.freeride.shop.service.SliderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/slide")
public class SlideController {

    private SliderService sliderService;

    public SlideController(SliderService sliderService) {
        this.sliderService = sliderService;
    }

    @GetMapping("/{slideNumber}")
    public ResponseEntity<byte[]> fetchImage(@PathVariable Integer slideNumber) {
        return ResponseEntity.ok(sliderService.getSlide(slideNumber));
    }
}
