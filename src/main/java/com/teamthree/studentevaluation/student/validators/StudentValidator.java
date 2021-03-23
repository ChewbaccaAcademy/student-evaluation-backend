package com.teamthree.studentevaluation.student.validators;

import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.student.UpdateStudentDto;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentValidator {

    private final MandatoryValuesValidator mandatoryValuesValidator;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentValidator(MandatoryValuesValidator mandatoryValuesValidator, StudentRepository studentRepository) {
        this.mandatoryValuesValidator = mandatoryValuesValidator;
        this.studentRepository = studentRepository;
    }

    public void validateStudentToAdd(String name, String lastname) {
        this.mandatoryValuesValidator.validate(name, "Name should consist only of characters.");
        this.mandatoryValuesValidator.validate(lastname, "Lastname should consist only of characters.");
    }

    public void validateStudentToUpdate(Long id, UpdateStudentDto updateStudentDto) {
        Optional.of(this.studentRepository.findById(id)).map(Optional::get).orElseThrow(StudentNotFoundException::new);
        this.mandatoryValuesValidator.validate(updateStudentDto.getName(), "Name should consist only of characters.");
        this.mandatoryValuesValidator.validate(updateStudentDto.getLastname(), "Lastname should consist only of characters.");
    }

}
