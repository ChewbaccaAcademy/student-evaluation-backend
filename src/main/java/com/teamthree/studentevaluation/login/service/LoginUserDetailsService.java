package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.login.models.LoginUserDetails;
import com.teamthree.studentevaluation.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    LoginUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found:" + username));
        return user.map(LoginUserDetails::new).get();

    }
}
