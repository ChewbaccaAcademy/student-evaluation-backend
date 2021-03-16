package com.teamthree.studentevaluation.student.validators;

import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageFormatValidator extends Validator<MultipartFile> {

    @Autowired
    public ImageFormatValidator() {
    }

    @Override
    public void validate(MultipartFile file, String message) {
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            throw new InvalidStudentFormException(message);
        }
    }
}
