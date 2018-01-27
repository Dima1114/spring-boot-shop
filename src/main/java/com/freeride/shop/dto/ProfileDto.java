package com.freeride.shop.dto;

import com.freeride.shop.entity.Role;
import com.freeride.shop.entity.User;
import com.freeride.shop.validation.StrongPassword;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileDto {

    private Long id;
    private String password;

    @StrongPassword(min = 6, max = 20, nullanle = true, message = "{StrongPassword.validation}")
    private String newPassword;

    private String firstName;
    private String lastName;

    @Email(regexp = ".{1,16}@.{1,12}\\..{2,4}")
    private String email;
    private String address;

    private List<String> roles;

    private MultipartFile image;

    public ProfileDto() {
    }

    public ProfileDto(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.newPassword = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
