package com.teamthree.studentevaluation.student.validators;

import com.teamthree.studentevaluation.student.exceptions.InvalidStudentEvaluationFormException;
import com.teamthree.studentevaluation.student.model.EvaluationDto;
import org.springframework.stereotype.Component;

@Component
public class EvaluateFormValidator extends  Validator<EvaluationDto>{

    @Override
    public void validate(EvaluationDto attribute, String message) {
        if (attribute.getEvaluation() < 1 || attribute.getEvaluation() > 5) {
            throw new InvalidStudentEvaluationFormException(message);
        }
    }
}
