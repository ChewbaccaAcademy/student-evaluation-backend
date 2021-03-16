package com.teamthree.studentevaluation.user.service;

import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.model.UserDto;
import com.teamthree.studentevaluation.user.model.LoginDto;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import com.teamthree.studentevaluation.user.validators.InputDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final InputDataValidator inputDataValidator;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(UserRepository userRepository, InputDataValidator inputDataValidator) {
        this.userRepository = userRepository;
        this.inputDataValidator = inputDataValidator;
    }

    public void registerNewUser(UserDto userDto) {
        inputDataValidator.isUsernameAvailable(userDto.getUsername());
        inputDataValidator.isEmailAvailable(userDto.getEmail());
        String passwordHash = encoder.encode(userDto.getPassword());
        User user = new User(userDto.getUsername(),
                passwordHash,
                userDto.getStream().name(),
                userDto.getEmail(),
                userDto.getStream().getRoleCode());
        userRepository.save(user);
    }

    public void checkLogin(LoginDto loginDto){
        String passwordHash = encoder.encode(loginDto.getPassword());
        inputDataValidator.isUserAvailableWithGivenCredentials(loginDto.getEmail(), passwordHash);

    }

}
