package com.teamthree.studentevaluation.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidStudentEvaluationFormException extends RuntimeException {
    public InvalidStudentEvaluationFormException(String message) {
        super(message);
    }
}
