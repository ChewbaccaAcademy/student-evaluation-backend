package com.teamthree.studentevaluation.login.controller;


import com.teamthree.studentevaluation.login.models.AuthenticationRequest;
import com.teamthree.studentevaluation.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @RequestMapping("/hello")
    public String index() {
        return "Hello world!";
    }


    // @PreAuthorize("permitAll")
    @RequestMapping("/hello2")
    public String index2() {
        return "Hello world!";
    }

    @PreAuthorize("permitAll")
    @CrossOrigin(origins = "https://team-three-frontend.herokuapp.com/")
    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody AuthenticationRequest
                                                    authenticationRequest) throws Exception {
        return loginService.authenticate(authenticationRequest);

    }
}