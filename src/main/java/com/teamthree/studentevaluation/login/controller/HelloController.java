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
public class HelloController {


    private LoginService loginService;

    @Autowired
    public HelloController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('admin')")    //example of authorisation for logged admin only
    @RequestMapping("/hello")
    public String index() {
        return "Hello world!";
    }


    @PreAuthorize("permitAll")                                      //example of permission for all
    @RequestMapping("/hello2")
    public String index2() {
        return "Hello world!";
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody AuthenticationRequest
                                                    authenticationRequest) throws Exception {
        return loginService.authenticate(authenticationRequest);

    }
}