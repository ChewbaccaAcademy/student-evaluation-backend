package com.teamthree.studentevaluation.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "actor")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @NotBlank()
    @Size(min = 5, max = 40)
    private String username;

    @NotNull
    @NotBlank()
    private String password;

    @NotNull
    private String stream;

    @NotNull
    @NotBlank()
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    private int roleId;

    public User() {
    }

    public User(@NotNull @NotBlank() @Size(min = 5, max = 40) String username, @NotNull @NotBlank() String password, @NotNull String stream, @NotNull @NotBlank() @Pattern(regexp = "^(.+)@(.+)$") String email, int roleId) {
        this.username = username;
        this.password = password;
        this.stream = stream;
        this.email = email;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStream() {
        return stream;
    }

    public String getEmail() {
        return email;
    }


}
