package com.freeride.shop.interseptors;

import com.freeride.shop.dto.SortDto;
import com.freeride.shop.entity.Item;
import com.freeride.shop.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SearchInterceptor extends HandlerInterceptorAdapter {

    private ItemService itemService;

    public SearchInterceptor(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);

        int page = 1;
        try {
            String param = request.getParameter("page");
            if (param != null)
                page = Integer.parseInt(param);
        } catch (NumberFormatException e) {
        }

        SortDto sortDto = ((SortDto) modelAndView.getModel().get("sortForm"));
        Page<Item> items = itemService.searchResults(sortDto, page);

        modelAndView.addObject("items", items.getContent());
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPages", items.getTotalPages());
    }
}
