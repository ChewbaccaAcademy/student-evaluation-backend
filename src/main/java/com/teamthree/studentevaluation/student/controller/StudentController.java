package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.model.AddStudentDto;
import com.teamthree.studentevaluation.student.model.UpdateStudentDto;
import com.teamthree.studentevaluation.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4401")
@RequestMapping("/student")
public class StudentController {

    final private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentService.getAllStudent();
        if (!students.isEmpty()) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        try {
            Student student = this.studentService.getStudentById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestPart("student") @Valid AddStudentDto studentDto, @RequestPart("image") @Nullable MultipartFile imageFile) {
        Student addStudent = this.studentService.addStudent(studentDto, imageFile);
        return new ResponseEntity<>(addStudent, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestPart("student") @Valid UpdateStudentDto studentDto, @RequestPart("image") @Nullable MultipartFile imageFile) {
        Student updateStudent = this.studentService.updateStudent(id, studentDto, imageFile);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable @NotNull @Valid Long id) {
        this.studentService.deleteStudentById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
