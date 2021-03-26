package com.teamthree.studentevaluation.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Student already exists.")
public class StudentAlreadyExistsException extends RuntimeException {
    public StudentAlreadyExistsException() {
        super();
    }
}
