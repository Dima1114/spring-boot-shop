package com.freeride.shop.listener;

import com.freeride.shop.entity.Brand;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.service.ItemService;
import com.freeride.shop.utils.AutowireUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PreRemove;
import java.util.Collections;
import java.util.List;

public class BrandEntityListener {

    @Autowired
    private ItemService itemService;

    @PreRemove
    public void preRemove(Brand brand){
        AutowireUtil.autowire(this, Collections.singletonList(itemService));

        long brandId = brand.getId();

        List<Item> items = itemService.listBrandItems(brandId);
        items.forEach(item -> item.setBrand(null));
        itemService.saveItems(items);
    }
}
