package com.teamthree.studentevaluation.student.validators;

import com.teamthree.studentevaluation.student.exceptions.InvalidStudentEvaluationFormException;
import com.teamthree.studentevaluation.student.model.evaluation.AddUpdateEvaluationDto;
import org.springframework.stereotype.Component;

@Component
public class EvaluateFormValidator extends Validator<AddUpdateEvaluationDto> {

    @Override
    public void validate(AddUpdateEvaluationDto attribute, String message) {
        if (attribute.getEvaluation() < 1 || attribute.getEvaluation() > 5) {
            throw new InvalidStudentEvaluationFormException(message);
        }
    }

    public void validateEvaluation(AddUpdateEvaluationDto attribute) {
        if (attribute.getEvaluation() < 1 || attribute.getEvaluation() > 5) {
            throw new InvalidStudentEvaluationFormException("Student evaluation should be between 1 and 5.");
        }
    }
}
