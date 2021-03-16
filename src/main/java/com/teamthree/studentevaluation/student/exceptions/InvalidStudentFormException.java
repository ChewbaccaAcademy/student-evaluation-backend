package com.teamthree.studentevaluation.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Name and lastname should only consist of characters.")
public class InvalidStudentFormException extends RuntimeException {
    public InvalidStudentFormException() {
        super();
    }

    public InvalidStudentFormException(String message) {
        super(message);
    }
}
