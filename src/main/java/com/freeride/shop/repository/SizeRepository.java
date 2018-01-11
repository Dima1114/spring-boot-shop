package com.freeride.shop.repository;


import com.freeride.shop.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {

        Size findBySize(String name);

        List<Size> findAllByType(String type);

        @Query(value = "select distinct s.type from Size s")
        List<String> findAllTypes();
//    @Modifying
//    @Query(value = "insert into size_item (item_id, size_id) values(Item.id, Size.id)")
//    void saveBySizeAndByItem(Size size, Item item);
}
