package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.student.entity.Image;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.student.AddStudentDto;
import com.teamthree.studentevaluation.student.model.student.UpdateStudentDto;
import com.teamthree.studentevaluation.student.repository.ImageRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.ImageFormatValidator;
import com.teamthree.studentevaluation.student.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.teamthree.studentevaluation.student.helper.ImageUtil.compressBytes;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ImageRepository imageRepository;
    private final StudentValidator studentValidator;
    private final ImageFormatValidator imageFormatValidator;

    @Autowired
    public StudentService(StudentRepository studentRepository, ImageRepository imageRepository, StudentValidator studentValidator, ImageFormatValidator imageFormatValidator) {
        this.studentRepository = studentRepository;
        this.imageRepository = imageRepository;
        this.studentValidator = studentValidator;
        this.imageFormatValidator = imageFormatValidator;
    }

    public List<Student> getAllStudent() {
        List<Student> students = this.studentRepository.findAll();
        students.forEach(student -> {
            if (student.getImage() != null)
                student.getImage().decompress();
        });
        return students;
    }

    public Student getStudentById(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (student.getImage() != null) {
            student.getImage().decompress();
        }
        return student;
    }

    public Student addStudent(AddStudentDto studentDto, MultipartFile imageFile) {
        this.studentValidator.validateStudentToAdd(studentDto.getName(), studentDto.getLastname());
        Image newImage = null;

        try {
            if (imageFile != null) {
                this.imageFormatValidator.validate(imageFile, "Incorrect image type. Should be: (png/jpg/jpeg)");
                Image img = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                        compressBytes(imageFile.getBytes()));
                newImage = this.imageRepository.save(img);
            }
        } catch (IOException e) {
            throw new InvalidStudentFormException("Failed to upload image.");
        }

        return this.studentRepository.save(new Student(
                true,
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

    public Student updateStudent(Long id, UpdateStudentDto studentDto, MultipartFile imageFile) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.studentValidator.validateStudentToUpdate(id, studentDto);
        Image newImage = student.getImage();

        try {
            if (imageFile != null) {
                if (newImage == null) {
                    newImage = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                } else {
                    newImage = new Image(newImage.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                }
                newImage = this.imageRepository.save(newImage);
            }
        } catch (IOException e) {
            throw new InvalidStudentFormException("Failed to upload image.");
        }

        return this.studentRepository.save(new Student(
                id,
                (studentDto.getIsActive() != null) ? studentDto.getIsActive() : student.isActive(),
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

}
