package com.freeride.shop.service;

import com.freeride.shop.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRole(String name);
    List<Role> getRoles();
}
