package com.freeride.shop.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/403")
    public String error403() {
        return "pages/error/403";
    }

    @RequestMapping("/error")
    public String error404() {
        return "pages/error/404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}