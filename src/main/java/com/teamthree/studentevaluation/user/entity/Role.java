package com.teamthree.studentevaluation.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank()
    private String roleType;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public String getRoleType() {
        return roleType;
    }
}
