package com.teamthree.studentevaluation.student.validators;

import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MandatoryValuesValidator extends Validator<String> {

    @Autowired
    public MandatoryValuesValidator() {
    }

    @Override
    public void validate(String attribute, String message) throws RuntimeException {
        if (!attribute.chars().allMatch(character -> Character.isLetter(character) || character == '-' || character == ' ')) {
            throw new InvalidStudentFormException(message);
        }
    }
}
