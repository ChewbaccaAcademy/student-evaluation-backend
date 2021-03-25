package com.teamthree.studentevaluation.login.controller;


import com.teamthree.studentevaluation.login.models.AuthenticationRequest;
import com.teamthree.studentevaluation.login.models.AuthenticationResponse;
import com.teamthree.studentevaluation.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PreAuthorize("permitAll")
    @CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest
                                                                    authenticationRequest) throws Exception {
        return loginService.authenticate(authenticationRequest);

    }
}