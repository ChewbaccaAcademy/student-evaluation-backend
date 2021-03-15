package com.teamthree.studentevaluation.user.model;

import com.teamthree.studentevaluation.user.entity.Stream;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 40)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).{8,50}$")
    private String password;

    @NotNull
    private Stream stream;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Stream getStream() {
        return stream;
    }

    public String getEmail() {
        return email;
    }
}
