package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.model.AddStudentDto;
import com.teamthree.studentevaluation.student.model.UpdateStudentDto;
import com.teamthree.studentevaluation.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    final private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("permitAll")
    @GetMapping(produces = "application/json")
    public List<Student> getAllStudents() {
        return this.studentService.getAllStudent();
    }

    @PreAuthorize("permitAll")
    @GetMapping("{id}")
    public Student getStudentById(@PathVariable Long id) {
        return this.studentService.getStudentById(id);
    }

    @CrossOrigin
    @PreAuthorize("permitAll")
    @PostMapping
    public Student addStudent(@RequestPart("student") @Valid AddStudentDto studentDto, BindingResult bindingResult, @RequestPart("image") @Nullable MultipartFile imageFile) {
        if (!bindingResult.hasErrors()) {
            return this.studentService.addStudent(studentDto, imageFile);
        }
        throw new InvalidStudentFormException("Invalid student form.");
    }

    @PreAuthorize("permitAll")
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestPart("student") @Valid UpdateStudentDto studentDto, BindingResult bindingResult, @RequestPart("image") @Nullable MultipartFile imageFile) {
        if (!bindingResult.hasErrors()) {
            return this.studentService.updateStudent(id, studentDto, imageFile);
        }
        throw new InvalidStudentFormException("Invalid student form.");
    }

}
