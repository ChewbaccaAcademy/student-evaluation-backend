package com.teamthree.studentevaluation.user.controller;

import com.teamthree.studentevaluation.user.exceptions.BadRegistrationFormException;
import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
    @CrossOrigin(origins = "https://team-three-frontend.herokuapp.com/")
    @RequestMapping("/signup")
    public void registerNewUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.registerNewUser(userDto);
        } else {
            throw new BadRegistrationFormException();
        }
    }

}
