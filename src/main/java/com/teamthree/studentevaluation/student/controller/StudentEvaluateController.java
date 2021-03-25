package com.teamthree.studentevaluation.student.controller;

import com.teamthree.studentevaluation.student.model.evaluation.AddUpdateEvaluationDto;
import com.teamthree.studentevaluation.student.model.evaluation.GetEvaluationDto;
import com.teamthree.studentevaluation.student.model.evaluation.GetUserEvaluationDto;
import com.teamthree.studentevaluation.student.service.StudentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student/evaluation")
@CrossOrigin(origins = {"https://team-three-frontend.herokuapp.com", "http://localhost:4200"})
@Validated
public class StudentEvaluateController {

    final private StudentEvaluationService studentEvaluationService;

    @Autowired
    public StudentEvaluateController(StudentEvaluationService studentEvaluationService) {
        this.studentEvaluationService = studentEvaluationService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<GetEvaluationDto> getEvaluations() {
        return this.studentEvaluationService.getStudentEvaluations();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public List<GetEvaluationDto> getUserStudentEvaluations(@PathVariable Long userId) {
        return this.studentEvaluationService.getUserStudentEvaluations(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/details")
    public List<GetUserEvaluationDto> getUserEvaluations() {
        return this.studentEvaluationService.getUserEvaluations();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studentId}")
    public List<GetEvaluationDto> getEvaluationsById(@PathVariable Long studentId) {
        return this.studentEvaluationService.getStudentEvaluationsById(studentId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{studentId}")
    public GetEvaluationDto addEvaluation(@RequestBody @Valid AddUpdateEvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId) {
        return this.studentEvaluationService.addStudentEvaluation(bindingResult, studentId, evaluationDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{studentId}/{evaluationId}")
    public GetEvaluationDto updateEvaluation(@RequestBody @Valid AddUpdateEvaluationDto evaluationDto, BindingResult bindingResult, @PathVariable Long studentId, @PathVariable Long evaluationId) {
        return this.studentEvaluationService.updateStudentEvaluation(bindingResult, evaluationId, studentId, evaluationDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{evaluationId}")
    public void disableEvaluation(@PathVariable Long evaluationId) {
        this.studentEvaluationService.disableEvaluation(evaluationId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/single/{evaluationId}")
    public GetEvaluationDto getSingleEvaluationById(@PathVariable Long evaluationId) {
        return this.studentEvaluationService.getEvaluationById(evaluationId);
    }
}