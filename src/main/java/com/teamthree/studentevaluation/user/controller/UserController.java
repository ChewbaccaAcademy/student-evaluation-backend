package com.teamthree.studentevaluation.user.controller;

import com.teamthree.studentevaluation.user.exceptions.BadRegistrationFormException;
import com.teamthree.studentevaluation.user.model.GetUserDto;
import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
    @RequestMapping("/signup")
    public void registerNewUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            this.userService.registerNewUser(userDto);
        } else {
            throw new BadRegistrationFormException();
        }
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
    @GetMapping("/users")
    public List<GetUserDto> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
    @GetMapping("/users/{userId}")
    public GetUserDto getUserById(@PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }

}
