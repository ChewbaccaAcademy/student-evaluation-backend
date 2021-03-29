package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.model.student.AddStudentDto;
import com.teamthree.studentevaluation.student.model.student.GetStudentWithAverageDto;
import com.teamthree.studentevaluation.student.model.student.UpdateStudentDto;
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
@CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
public class StudentController {

    final private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(produces = "application/json")
    public List<GetStudentWithAverageDto> getAllStudents() {
        return this.studentService.getAllStudent();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}")
    public GetStudentWithAverageDto getStudentById(@PathVariable Long id) {
        return this.studentService.getStudentById(id);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @PostMapping
    public Student addStudent(@RequestPart("student") @Valid AddStudentDto studentDto, BindingResult bindingResult, @RequestPart("image") @Nullable MultipartFile imageFile) {
        return this.studentService.addStudent(bindingResult, studentDto, imageFile);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestPart("student") @Valid UpdateStudentDto studentDto, BindingResult bindingResult, @RequestPart("image") @Nullable MultipartFile imageFile) {
        return this.studentService.updateStudent(bindingResult, id, studentDto, imageFile);
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @PutMapping("/disable/{id}")
    public void disableStudent(@PathVariable Long id) {
        this.studentService.disableStudent(id);
    }

}
