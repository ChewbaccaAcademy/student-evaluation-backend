package com.teamthree.studentevaluation.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid registration form.")
public class BadRegistrationFormException extends RuntimeException {
    public BadRegistrationFormException() {
        super();
    }
}