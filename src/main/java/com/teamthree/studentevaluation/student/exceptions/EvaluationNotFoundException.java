package com.teamthree.studentevaluation.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Evaluation not found.")
public class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException() {
        super();
    }
}
