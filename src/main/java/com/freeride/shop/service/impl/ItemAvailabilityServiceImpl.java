package com.freeride.shop.service.impl;

import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.ItemAvailability;
import com.freeride.shop.entity.Size;
import com.freeride.shop.repository.ItemAvailabilityRepository;
import com.freeride.shop.service.ItemAvailabilityService;
import com.freeride.shop.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class ItemAvailabilityServiceImpl implements ItemAvailabilityService {

    private ItemAvailabilityRepository itemAvailabilityRepository;
    private SizeService sizeService;

    public ItemAvailabilityServiceImpl(ItemAvailabilityRepository itemAvailabilityRepository, SizeService sizeService) {
        this.itemAvailabilityRepository = itemAvailabilityRepository;

        this.sizeService = sizeService;
    }

    @Override
    public List<ItemAvailability> getItemsAvailabilities(Item item) {
        return itemAvailabilityRepository.findAllByItemOrderBySize(item);
    }


    public List<Item> getItemsBySize(Size size) {
        return itemAvailabilityRepository.findAllBySize(size).stream()
                .map(ItemAvailability::getItem)
                .collect(Collectors.toList());
    }

    //    @Override
//    public List<Size> getItemSizes(Item item) {
//        return itemAvailabilityRepository.findAllByItemOrderBySize(item).stream()
//                .map(ItemAvailability::getSize)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<Size> getItemSizesWithPositiveQuantity(Item item) {
        return itemAvailabilityRepository.findAllByItemAndQuantityGreaterThanOrderBySize(item, 0).stream()
                .map(ItemAvailability::getSize)
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveAvailability(ItemAvailability itemAvailability) {
//        Size size = itemAvailability.getSize();
//        Item item = itemAvailability.getItem();
        //TODO fetch Categories List by EntityGraph
//        boolean isSizeAvailable = size.getAvailableInCategory().stream()
//                .anyMatch(category -> category.equals(item.getCategory()));
//        if (isSizeAvailable) {
        itemAvailabilityRepository.save(itemAvailability);
        return true;
//        }
//        return false;
    }

    @Override
    public void saveAvailabilities(Item item, Map<String, Integer> availabilities) {

        //TODO clean up
        Map<String, Integer> itemAvailabilities = availabilities.entrySet().stream()
                .filter(entry -> !Objects.isNull(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        List<ItemAvailability> itemAvailabilityList = Optional.ofNullable(getItemsAvailabilities(item))
                .orElseGet(Collections::emptyList);

        for (Map.Entry<String, Integer> ia : itemAvailabilities.entrySet()) {
            Size size = sizeService.getSize(ia.getKey());
            int quantity = ia.getValue();
            if (size == null) {
                continue;
            }

            ItemAvailability itemAvailability = itemAvailabilityList.stream()
                    .filter(a -> a.getSize().equals(size))
                    .findFirst()
                    .orElseGet(() -> createNewItemAvailability(item, size));

            itemAvailability.setQuantity(quantity);
            itemAvailabilityRepository.save(itemAvailability);
        }
    }

    private ItemAvailability createNewItemAvailability(Item item, Size size) {
        ItemAvailability itemAvailability = new ItemAvailability();
        itemAvailability.setItem(item);
        itemAvailability.setSize(size);
        return itemAvailability;
    }
}
