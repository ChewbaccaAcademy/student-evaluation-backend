package com.teamthree.studentevaluation.student.controller;


import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.model.AddEvaluationDto;
import com.teamthree.studentevaluation.student.model.GetEvaluationDto;
import com.teamthree.studentevaluation.student.service.StudentEvaluationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student/evaluate")
@CrossOrigin
public class StudentEvaluateController {

    final private StudentEvaluationService studentEvaluationService;

    public StudentEvaluateController(StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationService = studentEvaluationService;
    }

    @PreAuthorize("permitAll")
    @GetMapping
    public List<Evaluation> getEvaluations() {
        return this.studentEvaluationService.getStudentEvaluations();
    }

    @PreAuthorize("permitAll")
    @GetMapping("{id}")
    public List<GetEvaluationDto> getEvaluationsById(@PathVariable Long id) {
        return this.studentEvaluationService.getStudentEvaluationsById(id);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/{studentId}")
    public Evaluation addEvaluation(@RequestBody @Valid AddEvaluationDto addEvaluationDto, BindingResult bindingResult, @PathVariable Long studentId, @RequestParam Long userId) {
        if (!bindingResult.hasErrors()) {
            return this.studentEvaluationService.addStudentEvaluation(studentId, userId, addEvaluationDto);
        }
        throw new InvalidStudentFormException("Invalid student form.");
    }
}