package com.teamthree.studentevaluation.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email already exists.")
public class EmailIsAlreadyInUseException extends RuntimeException {
    public EmailIsAlreadyInUseException() {
        super();
    }
}
