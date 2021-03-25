package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.login.models.LoginUserDetails;
import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public LoginUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public LoginUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found:" + email));
        return new LoginUserDetails(user.get().getId(),
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getUsername(),
                user.get().getStream(),
                user.get().getRole());
    }
}
