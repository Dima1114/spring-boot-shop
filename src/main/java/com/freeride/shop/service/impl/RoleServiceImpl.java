package com.freeride.shop.service.impl;

import com.freeride.shop.entity.Role;
import com.freeride.shop.repository.RoleRepository;
import com.freeride.shop.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
