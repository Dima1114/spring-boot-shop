package com.freeride.shop.listener;


import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.utils.AutowireUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PreRemove;
import java.util.Collections;
import java.util.List;

public class CategoryEntityListener {

    //use autowire because class is not a spring component(bean)
    @Autowired
    private ItemService itemService;

    @PreRemove
    public void preRemove(Category category){
        AutowireUtil.autowire(this, Collections.singletonList(itemService));

        long categoryId = category.getId();

        List<Item> items = itemService.listCategoryItems(categoryId);
        items.forEach(item -> item.setCategory(null));
        itemService.saveItems(items);
    }
}
