package com.teamthree.studentevaluation.login.models;

import com.teamthree.studentevaluation.user.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LoginUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String stream;
    private final String email;
    private final List<GrantedAuthority> role;

    public LoginUserDetails(Long id, String email, String password, String username, String stream, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.stream = stream;
        this.role = Arrays.asList(new SimpleGrantedAuthority(role.getRoleType()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
