package com.teamthree.studentevaluation.user.controller;

import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.model.LoginDto;
import com.teamthree.studentevaluation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @CrossOrigin
    @RequestMapping("/signup")
    public void registerNewUser(@Valid @RequestBody UserDto userDto) {
        userService.registerNewUser(userDto);
    }

    @PostMapping
    @CrossOrigin
    @RequestMapping("/login")
    public void login(@Valid @RequestBody LoginDto login) { userService.checkLogin(login); }
}
