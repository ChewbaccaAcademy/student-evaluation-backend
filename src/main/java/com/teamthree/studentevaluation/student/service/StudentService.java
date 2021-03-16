package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.student.entity.Image;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.AddStudentDto;
import com.teamthree.studentevaluation.student.model.UpdateStudentDto;
import com.teamthree.studentevaluation.student.repository.ImageRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.ImageFormatValidator;
import com.teamthree.studentevaluation.student.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
        for (Student tempStud : students) {
            if (tempStud.getImage() != null) {
                tempStud.getImage().decompress();
            }
        }
        return students;
    }

    public Student getStudentById(Long id) {
        Student student = Optional.of(this.studentRepository.findById(id)).map(Optional::get).orElseThrow(StudentNotFoundException::new);
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
            throw new InvalidStudentFormException("Image is exceeding maximum size: (500kb).");
        }

        return this.studentRepository.save(new Student(studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

    public Student updateStudent(Long id, UpdateStudentDto studentDto, MultipartFile imageFile) {
        this.studentValidator.validateStudentToUpdate(id, studentDto);
        Optional<Student> retrievedStudent = Optional.of(this.studentRepository.findById(id)).orElseThrow(StudentNotFoundException::new);
        Image newImage = null;
        if (retrievedStudent.isPresent() && retrievedStudent.get().getImage() != null) {
            newImage = retrievedStudent.get().getImage();
        }
        try {
            if (imageFile != null && newImage != null) {
                newImage = new Image(newImage.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                newImage = this.imageRepository.save(newImage);
            } else if (imageFile != null) {
                newImage = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                newImage = this.imageRepository.save(newImage);
            }
        } catch (IOException e) {
            throw new InvalidStudentFormException("Image is exceeding maximum size: (500kb).");
        }

        return this.studentRepository.save(new Student(id,
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

    public void deleteStudentById(Long id) {
        Student student = Optional.of(this.studentRepository.findById(id)).map(Optional::get).orElseThrow(StudentNotFoundException::new);
        this.studentRepository.delete(student);
    }

}
