package com.teamthree.studentevaluation.service;

import com.teamthree.studentevaluation.LoginUserDetailsService;
import com.teamthree.studentevaluation.models.AuthenticationRequest;
import com.teamthree.studentevaluation.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {


    private LoginUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;

    @Autowired
    public LoginServiceImplementation(LoginUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public String authenticate(AuthenticationRequest
                                    authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest
                    .getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password!", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return jwt;
    }
}
