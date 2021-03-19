package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.model.EvaluationDto;
import com.teamthree.studentevaluation.student.model.GetEvaluationDto;
import com.teamthree.studentevaluation.student.service.StudentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student/evaluate")
@CrossOrigin
@Validated
public class StudentEvaluateController {

    final private StudentEvaluationService studentEvaluationService;

    @Autowired
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
    public Evaluation addEvaluation(@RequestBody @Valid EvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId, @RequestParam Long userId) {
        if (!bindingResult.hasErrors()) {
            return this.studentEvaluationService.addStudentEvaluation(studentId, userId, evaluationDto);
        }
        throw new InvalidStudentFormException("Invalid evaluation form values.");
    }

    /*@PreAuthorize("permitAll")
    @PutMapping("/{studentId}")
    public Evaluation updateEvaluation(@RequestBody @Valid EvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId, @RequestParam Long userId) {
        if (!bindingResult.hasErrors()) {
            return this.studentEvaluationService.updateStudentEvaluation(studentId, userId, evaluationDto);
        }
        throw new InvalidStudentFormException("Invalid evaluation form values.");
    }*/

}