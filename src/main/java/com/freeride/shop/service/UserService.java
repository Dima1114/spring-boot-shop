package com.freeride.shop.service;

import com.freeride.shop.dto.ProfileDto;
import com.freeride.shop.dto.RegistrationDto;
import com.freeride.shop.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> list(int page);
    void registerUser(RegistrationDto profileDto);
    void updateUser(Long userId, ProfileDto profileDto);
    User getUserByName(String username);
    User getUser(Long userId);
    byte[] image(Long id);
    long countUsers();
    void deleteUser(Long userId);



}
