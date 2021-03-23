package com.teamthree.studentevaluation.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad email or password.")
public class IncorrectUserOrEmailException extends RuntimeException {
    public IncorrectUserOrEmailException(String message) {
        super(message);
    }
}
