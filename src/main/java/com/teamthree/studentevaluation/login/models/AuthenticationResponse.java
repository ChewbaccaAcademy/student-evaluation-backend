package com.teamthree.studentevaluation.login.models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationResponse {
    private final String jwt;
    private final List<String> role;
    private final long date;
    private final long userId;


    public AuthenticationResponse(String jwt, Collection<? extends GrantedAuthority> role, long date, long userId) {
        this.jwt = jwt;
        this.role = role.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        this.date = date;
        this.userId = userId;
    }

    public String getJwt() {
        return jwt;
    }

    public List<String> getRole() {
        return role;
    }

    public long getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }
}
