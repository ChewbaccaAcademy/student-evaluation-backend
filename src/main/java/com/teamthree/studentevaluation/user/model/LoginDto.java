package com.teamthree.studentevaluation.user.model;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginDto {

    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z]).{8,50}$")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    public String getPassword() { return password; }

    public String getEmail() {
        return email;
    }
}
