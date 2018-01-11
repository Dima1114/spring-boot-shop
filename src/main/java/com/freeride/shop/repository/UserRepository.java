package com.freeride.shop.repository;

import com.freeride.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsername(String username);
}
