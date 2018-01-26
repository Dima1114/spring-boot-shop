package com.freeride.shop.repository;

import com.freeride.shop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);
}
