package com.teamthree.studentevaluation.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username already exists.")
public class UsernameIsAlreadyInUseException extends RuntimeException {
    public UsernameIsAlreadyInUseException() {
        super();
    }
}
