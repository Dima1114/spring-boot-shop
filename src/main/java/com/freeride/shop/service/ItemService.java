package com.freeride.shop.service;

import com.freeride.shop.dto.ItemDto;
import com.freeride.shop.dto.SortDto;
import com.freeride.shop.entity.Item;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    Page<Item> list(int page);
    Page<Item> listCategoryItems(Long categoryId, int page);
    Page<Item> searchResults(SortDto sortDto, int page);

    Page<Item> adminSearchResults(String name, int page);
    Item getItem(Long id);
    List<Item> listMostRated();
    List<Item> listCategoryItems(Long categoryId);
    List<Item> listBrandItems(Long brandId);

    void saveItems(List<Item> items);
    byte[] getImage(Long id);
    void saveItem(ItemDto itemDto);
    void saveImage(Item item, byte[] img);
    void deleteItem(Long itemId);
    long countItems();
}
