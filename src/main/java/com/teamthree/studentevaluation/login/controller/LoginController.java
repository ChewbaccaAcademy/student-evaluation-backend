package com.teamthree.studentevaluation.login.controller;


import com.teamthree.studentevaluation.login.models.AuthenticationRequest;
import com.teamthree.studentevaluation.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody AuthenticationRequest
                                                    authenticationRequest) throws Exception {
        return loginService.authenticate(authenticationRequest);

    }
}