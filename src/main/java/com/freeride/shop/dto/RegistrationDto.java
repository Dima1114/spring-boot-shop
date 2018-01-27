package com.freeride.shop.dto;

import com.freeride.shop.validation.StrongPassword;
import com.freeride.shop.validation.UniqueUsername;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDto {

    @Size(min = 4, max = 20, message = "{Size.registrationDto.login}")
    @Pattern(regexp = "[a-zA-Z]\\w+", message = "{Pattern.registrationDto.login}")
    @UniqueUsername
    private String login;

    @StrongPassword(min = 6, max = 20, message = "{StrongPassword.validation}")
    private String password;

    @Email(regexp = ".{1,25}@.{1,15}\\..{2,4}")
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationDto that = (RegistrationDto) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
