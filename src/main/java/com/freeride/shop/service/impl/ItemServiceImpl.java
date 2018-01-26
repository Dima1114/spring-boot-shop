package com.freeride.shop.service.impl;

import com.freeride.shop.dto.ItemDto;
import com.freeride.shop.dto.SortDto;
import com.freeride.shop.entity.Brand;
import com.freeride.shop.entity.Category;
import com.freeride.shop.entity.Item;
import com.freeride.shop.repository.ItemRepository;
import com.freeride.shop.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final static String DEFAULT_IMAGE = "0";

    private ItemRepository itemRepository;
    private String imagePath;
    private CategoryService categoryService;
    private BrandService brandService;
    private SizeService sizeService;
    private ItemAvailabilityService itemAvailabilityService;

    public ItemServiceImpl(ItemRepository itemRepository, @Value("${images.item.path}") String imagePath, CategoryService categoryService, BrandService brandService, SizeService sizeService, ItemAvailabilityService itemAvailabilityService) {
        this.itemRepository = itemRepository;
        this.imagePath = imagePath;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.sizeService = sizeService;
        this.itemAvailabilityService = itemAvailabilityService;
    }

    @Override
    public long countItems() {
        return itemRepository.count();
    }

    @Override
    public Page<Item> list(int page) {
        Pageable pageable = new PageRequest(page - 1, 12, new Sort("name"));
        return itemRepository.findAll(pageable);
    }

    @Override
    public Page<Item> listCategoryItems(Long categoryId, int page) {
        Pageable pageable = new PageRequest(page - 1, 12, new Sort("name"));
        return itemRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Page<Item> searchResults(SortDto sortDto, int page) {

        String category = sortDto.getCategory();
        String name = sortDto.getName();
        List<String> brands = sortDto.getBrandsDto();
        if (brands.isEmpty()) {
            brands = brandService.brandsList().stream().map(Brand::getName).collect(Collectors.toList());
        }
        BigDecimal minPrice = sortDto.getMinPrice();
        BigDecimal maxPrice = sortDto.getMaxPrice();
        List<Long> itemIds = itemAvailabilityService.getItemsBySize(
                sizeService.getSize(sortDto.getSize()))
                .stream()
                .map(Item::getId)
                .collect(Collectors.toList());
        if(itemIds.isEmpty()){
            itemIds = itemRepository.findAll().stream()
                    .map(Item::getId)
                    .collect(Collectors.toList());
        }
        String sortBy = sortDto.getSortBy();
        boolean ascending = sortDto.isAscending();

        return sortList(itemIds, category, name, brands, minPrice, maxPrice, page, sortBy, ascending);
    }

    //with sorting
    private Page<Item> sortList(List<Long> itemIds, String category, String name, List<String> brands,
                                BigDecimal minPrice, BigDecimal maxPrice,
                                int page, String sortBy, boolean ascending) {
        Pageable pageable = new PageRequest(page - 1, 12,
                new Sort(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        return itemRepository.findAllByIdInAndCategory_NameContainingAndNameContainingAndBrand_NameInAndPriceBetween(
                itemIds, category, name, brands, minPrice, maxPrice, pageable);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public List<Item> listMostRated() {
        return itemRepository.findTop6ByOrderByRatingDesc();
    }

    @Override
    public void saveItem(ItemDto itemDto) {
        Item item = new Item();

        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());

        Brand brand = brandService.getBrand(itemDto.getBrand());
        item.setBrand(brand);

        Category category = categoryService.getCategory(itemDto.getCategory());
        item.setCategory(category);

        LocalDateTime arrivalDate = convertToLocalDateTime(itemDto.getArrivalDate());
        item.setArrivalDate(arrivalDate);

        item = itemRepository.save(item);

        itemAvailabilityService.saveAvailabilities(item, itemDto.getSizeQuantity());

        byte[] img = null;
        try {
            img = itemDto.getImage().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (img != null || img.length != 0) {
            saveImage(item, img);
        }
    }

    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Override
    public byte[] getImage(Long id) {
        Item item = itemRepository.findOne(id);
        byte[] img;
        try {
            if (item == null) {//wtf?
                img = Files.readAllBytes(Paths.get(imagePath, DEFAULT_IMAGE));
            } else {
                img = Files.readAllBytes(Paths.get(imagePath ,
                        item.getName() + " " + item.getId() + ".jpg"));
            }
            return img;

        } catch (IOException e) {
            //try to getCategory default getImage
            try {
                return Files.readAllBytes(Paths.get(imagePath, DEFAULT_IMAGE));
            } catch (IOException e1) {
                e1.printStackTrace();
                return new byte[0];
            }
        }
    }

    @Override
    public void saveImage(Item item, byte[] img) {

        if (img.length != 0) {
            try {
                Files.write(Paths.get(imagePath,
                        item.getName() + " " + item.getId() + ".jpg"), img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteItem(Long itemId) {

        Item item = itemRepository.findOne(itemId);
        deleteImage(item);
        itemRepository.delete(itemId);
    }

    private void deleteImage(Item item){
        try {
            Files.deleteIfExists(Paths.get(imagePath + "/" + item.getCategory().getName().toLowerCase(),
                    item.getName() + " " + item.getId() + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> listCategoryItems(Long categoryId) {
        return itemRepository.findAllByCategory_Id(categoryId);
    }

    @Override
    public List<Item> listBrandItems(Long brandId) {
        return itemRepository.findAllByBrand_Id(brandId);
    }

    @Override
    public void saveItems(List<Item> items) {
        itemRepository.save(items);
    }
}
