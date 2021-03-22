package com.teamthree.studentevaluation.login.service;

import com.teamthree.studentevaluation.login.exceptions.IncorrectUserOrEmailException;
import com.teamthree.studentevaluation.login.models.AuthenticationRequest;
import com.teamthree.studentevaluation.login.models.AuthenticationResponse;
import com.teamthree.studentevaluation.login.models.LoginUserDetails;
import com.teamthree.studentevaluation.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementation implements LoginService {


    private final LoginUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    @Autowired
    public LoginServiceImplementation(LoginUserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest
                                                       authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest
                    .getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new IncorrectUserOrEmailException("Incorrect username or password!");
        }
        final LoginUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt, userDetails.getAuthorities(), jwtTokenUtil.extractExpiration(jwt).getTime(), userDetails.getId());
    }
}
