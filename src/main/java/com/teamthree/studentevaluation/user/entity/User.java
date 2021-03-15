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
    private int id;

    @NotBlank()
    @Size(min = 5, max = 40)
    private String username;

    @NotBlank()
    private String password;

    @NotNull
    private String stream;

    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public User() {
    }

    public User(@NotBlank() @Size(min = 5, max = 40) String username, @NotBlank() String password, @NotNull String stream, @NotBlank() @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") String email, Role role) {
        this.username = username;
        this.password = password;
        this.stream = stream;
        this.email = email;
        this.role = role;
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
