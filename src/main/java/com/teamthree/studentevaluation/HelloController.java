package com.teamthree.studentevaluation;


import com.teamthree.studentevaluation.models.AuthenticationRequest;
import com.teamthree.studentevaluation.service.LoginService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/hello")
    public String index() {
        return "Hello world!";
    }

    @RequestMapping("/hello2")
    public String index2() {
        return "Hello world!";
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public String createAuthenticationToken(@RequestBody AuthenticationRequest
                                                 authenticationRequest) throws Exception {
        return loginService.authenticate(authenticationRequest);

    }
}