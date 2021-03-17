package com.teamthree.studentevaluation.user.controller;

import com.teamthree.studentevaluation.user.exceptions.BadRegisterFormException;
import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void registerNewUser(@Validated @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.registerNewUser(userDto);
        } else {
            throw new BadRegisterFormException();
        }
    }

}
