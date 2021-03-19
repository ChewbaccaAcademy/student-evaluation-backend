package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.login.models.LoginUserDetails;
import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.model.AddUpdateEvaluationDto;
import com.teamthree.studentevaluation.student.model.GetEvaluationDto;
import com.teamthree.studentevaluation.student.service.StudentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student/evaluation")
@CrossOrigin
@Validated
public class StudentEvaluateController {

    final private StudentEvaluationService studentEvaluationService;

    @Autowired
    public StudentEvaluateController(StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationService = studentEvaluationService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<Evaluation> getEvaluations() {
        return this.studentEvaluationService.getStudentEvaluations();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public List<GetEvaluationDto> getUserStudentEvaluations(@PathVariable Long userId) {
        return this.studentEvaluationService.getUserStudentEvaluations(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studentId}")
    public List<GetEvaluationDto> getEvaluationsById(@PathVariable Long studentId) {
        return this.studentEvaluationService.getStudentEvaluationsById(studentId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{studentId}")
    public Evaluation addEvaluation(@RequestBody @Valid AddUpdateEvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId) {
        Long userId = ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!bindingResult.hasErrors()) {
            return this.studentEvaluationService.addStudentEvaluation(studentId, userId, evaluationDto);
        }
        throw new InvalidStudentFormException("Invalid evaluation form values.");
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{studentId}/{evaluationId}")
    public Evaluation updateEvaluation(@RequestBody @Valid AddUpdateEvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId, @PathVariable Long evaluationId) {
        Long userId = ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!bindingResult.hasErrors()) {
            return this.studentEvaluationService.updateStudentEvaluation(evaluationId, studentId, userId, evaluationDto);
        }
        throw new InvalidStudentFormException("Invalid evaluation form values.");
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{studentId}/{evaluationId}")
    public void deleteStudentEvaluation(@PathVariable Long studentId, @PathVariable Long evaluationId) {
        Long userId = ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        this.studentEvaluationService.deleteStudentEvaluation(evaluationId, studentId, userId);
    }

}