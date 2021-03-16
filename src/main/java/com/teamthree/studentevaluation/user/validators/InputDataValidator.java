package com.teamthree.studentevaluation.user.validators;

import com.teamthree.studentevaluation.user.exceptions.BadLoginCredentialsException;
import com.teamthree.studentevaluation.user.exceptions.EmailIsAlreadyInUseException;
import com.teamthree.studentevaluation.user.exceptions.UsernameIsAlreadyInUseException;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputDataValidator {

    private final UserRepository userRepository;

    @Autowired
    public InputDataValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void isUsernameAvailable(String username) throws UsernameIsAlreadyInUseException {
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new UsernameIsAlreadyInUseException();
        }
    }

    public void isEmailAvailable(String email) throws EmailIsAlreadyInUseException {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new EmailIsAlreadyInUseException();
        }
    }

    public void isUserAvailableWithGivenCredentials(String email, String password) throws BadLoginCredentialsException {
        if (!userRepository.findUserByUsernameAndPassword(email, password).isPresent()) {
            throw new BadLoginCredentialsException();
        }
    }


}
