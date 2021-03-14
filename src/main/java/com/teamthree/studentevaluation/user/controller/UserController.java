package com.teamthree.studentevaluation.user.controller;

import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signup")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void registerNewUser(@RequestBody @Valid UserDto userDto) {
        userService.registerNewUser(userDto);
    }

}
