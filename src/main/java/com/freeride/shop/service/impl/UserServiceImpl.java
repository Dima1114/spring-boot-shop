package com.freeride.shop.service.impl;

import com.freeride.shop.dto.ProfileDto;
import com.freeride.shop.dto.RegistrationDto;
import com.freeride.shop.entity.Role;
import com.freeride.shop.entity.User;
import com.freeride.shop.repository.UserRepository;
import com.freeride.shop.service.RoleService;
import com.freeride.shop.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final long DEFAULT_IMAGE = 0L;

    private UserRepository userRepository;
    private RoleService roleService;
    private String imagePath;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService, @Value("${images.user.path}") String imagePath) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.imagePath = imagePath;
    }

    @Override
    public Page<User> list(int page) {
        Pageable pageable = new PageRequest(page - 1, 12, new Sort("username"));
        return userRepository.findAll(pageable);
    }

    @Override
    public long countUsers() {
        return userRepository.count();
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getLogin());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());

        Role userRole = roleService.getRole("user");
        user.setRoles(Collections.singletonList(userRole));

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, ProfileDto profileDto) {
        User user = userRepository.findOne(userId);

        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        user.setEmail(profileDto.getEmail());
        user.setAddress(profileDto.getAddress());

        if (user.getPassword().equals(profileDto.getPassword())) {
            if (profileDto.getNewPassword() != null) {
                user.setPassword(profileDto.getNewPassword());
            }
        }

        List<String> roles = profileDto.getRoles();
        if (roles != null && roles.size() > 0) {
            user.setRoles(roles.stream().map(r -> roleService.getRole(r)).collect(Collectors.toList()));
        }

        userRepository.save(user);

        if (profileDto.getImage() != null && !profileDto.getImage().isEmpty()) {
            try {
                Files.write(Paths.get(imagePath, user.getId() + ".jpg"),
                        profileDto.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public byte[] image(Long id) {
        Path path = Paths.get(imagePath, id.toString() + ".jpg");
        try {
            if (Files.exists(path)) {
                return Files.readAllBytes(path);
            }
            return Files.readAllBytes(Paths.get(imagePath, DEFAULT_IMAGE + ".jpg"));
        } catch (IOException e) {
            return new byte[0];

        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
