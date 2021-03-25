package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.service.SearchStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
public class SearchController {

    final private SearchStudentService searchStudentService;

    @Autowired
    public SearchController(SearchStudentService searchStudentService) {
        this.searchStudentService = searchStudentService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/student/{value}")
    public List<Student> getStudentByValue(@PathVariable @Size(min = 3, max = 512) String value) {
        return this.searchStudentService.getStudentsByValue(value);
    }

}
