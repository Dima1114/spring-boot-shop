package com.freeride.shop.repository;

import com.freeride.shop.entity.Cart;
import com.freeride.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Long>{

    List<Cart> findAllByUser(User user);

    Cart findFirstByUserAndItemAvailability_Id(User user, Long availId);
//    void deleteCartByUserAndItemAvailability_Id(User user, Long availId);

}
