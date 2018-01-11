package com.freeride.shop.service;

import com.freeride.shop.entity.Size;

import java.util.List;

public interface SizeService {

    List<Size> getSizes();

    List<Size> getTypeSizes(String type);
    List<String> getTypes();

    Size getSize(String name);
}
