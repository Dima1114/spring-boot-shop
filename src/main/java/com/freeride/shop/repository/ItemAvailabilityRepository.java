package com.freeride.shop.repository;

import com.freeride.shop.entity.Item;
import com.freeride.shop.entity.ItemAvailability;
import com.freeride.shop.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemAvailabilityRepository extends JpaRepository<ItemAvailability, Long> {

    List<ItemAvailability> findAllByItemOrderBySize(Item item);
    List<ItemAvailability> findAllByItemAndQuantityGreaterThanOrderBySize(Item item, int quantity);
    List<ItemAvailability> findAllBySize(Size size);
    ItemAvailability findByItem_IdAndSize_Id(Long itemId, Long sizeId);
}
