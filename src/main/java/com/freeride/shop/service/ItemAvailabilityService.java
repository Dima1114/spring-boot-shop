package com.freeride.shop.service;

import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.ItemAvailability;
import com.freeride.shop.entity.Size;

import java.util.List;
import java.util.Map;

public interface ItemAvailabilityService {

    List<ItemAvailability> getItemsAvailabilities(Item item);
    List<Size> getItemSizesWithPositiveQuantity(Item item);
    List<Item> getItemsBySize(Size size);
    boolean saveAvailability(ItemAvailability itemAvailability);
    void saveAvailabilities(Item item, Map<String, Integer> availabilities);
}
