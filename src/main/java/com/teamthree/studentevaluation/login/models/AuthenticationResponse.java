package com.teamthree.studentevaluation.login.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationResponse {
    private final String jwt;
    private final String role;
    private final long date;


    public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> role, long date) {
        this.jwt = jwt;
        this.role = role.stream().findFirst().get().toString();
        this.date = date;
    }

    public String getJwt() {
        return jwt;
    }

    public String getRole() {
        return role;
    }

    public long getDate() {
        return date;
    }
}
