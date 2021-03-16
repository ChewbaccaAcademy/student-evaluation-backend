package com.teamthree.studentevaluation.login.models;

import com.teamthree.studentevaluation.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LoginUserDetails implements UserDetails {


    private String username;
    private String password;
    private String stream;
    private String email;
    private List<GrantedAuthority> role;

    public LoginUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.stream = user.getStream();
        this.username = user.getUsername();
        this.role = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleType()));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
