package com.freeride.shop.interseptors;

import com.freeride.shop.service.SizeService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CategoryInterceptor extends HandlerInterceptorAdapter{

    private SizeService sizeService;

    public CategoryInterceptor(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("sizeTypes", sizeService.getTypes());
    }
}
