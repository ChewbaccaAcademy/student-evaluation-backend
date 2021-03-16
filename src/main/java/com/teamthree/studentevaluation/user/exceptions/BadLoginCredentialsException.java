package com.teamthree.studentevaluation.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Login credentials are invalid.")
public class BadLoginCredentialsException extends RuntimeException {
    public BadLoginCredentialsException() {
        super();
    }
}
