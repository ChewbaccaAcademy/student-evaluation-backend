package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.login.models.LoginUserDetails;
import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.EvaluationNotFoundException;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.AddUpdateEvaluationDto;
import com.teamthree.studentevaluation.student.model.GetEvaluationDto;
import com.teamthree.studentevaluation.student.repository.EvaluationRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.EvaluateFormValidator;
import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.exceptions.UserNotFoundException;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentEvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final EvaluateFormValidator evaluateFormValidator;

    @Autowired
    public StudentEvaluationService(StudentRepository studentRepository, EvaluationRepository evaluationRepository, UserRepository userRepository, EvaluateFormValidator evaluateFormValidator) {
        this.studentRepository = studentRepository;
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.evaluateFormValidator = evaluateFormValidator;
    }

    public List<GetEvaluationDto> getStudentEvaluations() {
        return this.evaluationRepository.findAll().stream().map(evaluation -> new GetEvaluationDto(
                evaluation.getId(),
                evaluation.isActive(),
                evaluation.getStudentId(),
                evaluation.getUserId(),
                (evaluation.getStream() != null) ? evaluation.getStream().toString() : null,
                (evaluation.getCommunication() != null) ? evaluation.getCommunication().toString() : null,
                (evaluation.getLearnAbility() != null) ? evaluation.getLearnAbility().toString() : null,
                (evaluation.getDirection() != null) ? evaluation.getDirection().toString() : null,
                evaluation.getEvaluation(),
                evaluation.getComment(),
                evaluation.getTimestamp())).collect(Collectors.toList());
    }

    public List<GetEvaluationDto> getUserStudentEvaluations(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return this.evaluationRepository.findByUser(user).orElseThrow(UserNotFoundException::new).stream().map(evaluation -> new GetEvaluationDto(
                evaluation.getId(),
                evaluation.isActive(),
                evaluation.getStudentId(),
                evaluation.getUserId(),
                (evaluation.getStream() != null) ? evaluation.getStream().toString() : null,
                (evaluation.getCommunication() != null) ? evaluation.getCommunication().toString() : null,
                (evaluation.getLearnAbility() != null) ? evaluation.getLearnAbility().toString() : null,
                (evaluation.getDirection() != null) ? evaluation.getDirection().toString() : null,
                evaluation.getEvaluation(),
                evaluation.getComment(),
                evaluation.getTimestamp())).collect(Collectors.toList());
    }

    public List<GetEvaluationDto> getStudentEvaluationsById(Long studentId) {
        Student foundStudent = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<Evaluation> evaluations = this.evaluationRepository.findByStudent(foundStudent).orElseThrow(StudentNotFoundException::new);
        return evaluations.stream().map(evaluation -> new GetEvaluationDto(
                evaluation.getId(),
                evaluation.isActive(),
                evaluation.getStudentId(),
                evaluation.getUserId(),
                (evaluation.getStream() != null) ? evaluation.getStream().toString() : null,
                (evaluation.getCommunication() != null) ? evaluation.getCommunication().toString() : null,
                (evaluation.getLearnAbility() != null) ? evaluation.getLearnAbility().toString() : null,
                (evaluation.getDirection() != null) ? evaluation.getDirection().toString() : null,
                evaluation.getEvaluation(),
                evaluation.getComment(),
                evaluation.getTimestamp())).collect(Collectors.toList());
    }

    public GetEvaluationDto addStudentEvaluation(BindingResult bindingResult, Long studentId, AddUpdateEvaluationDto evaluationDto) {
        Long userId = ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!bindingResult.hasErrors()) {
            evaluateFormValidator.validateEvaluation(evaluationDto);
            Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
            User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Evaluation newEvaluation = this.evaluationRepository.save(new Evaluation.EvaluationBuilder(student, user)
                    .setStream(evaluationDto.getStream())
                    .setCommunication(evaluationDto.getCommunication())
                    .setLearnAbility(evaluationDto.getLearnAbility())
                    .setDirection(evaluationDto.getDirection())
                    .setEvaluation(evaluationDto.getEvaluation())
                    .setComment(evaluationDto.getComment())
                    .setIsActive(true).build());

            return new GetEvaluationDto(newEvaluation.getId(),
                    newEvaluation.isActive(),
                    newEvaluation.getStudentId(),
                    newEvaluation.getUserId(),
                    (newEvaluation.getStream() != null) ? newEvaluation.getStream().toString() : null,
                    (newEvaluation.getCommunication() != null) ? newEvaluation.getCommunication().toString() : null,
                    (newEvaluation.getLearnAbility() != null) ? newEvaluation.getLearnAbility().toString() : null,
                    (newEvaluation.getDirection() != null) ? newEvaluation.getDirection().toString() : null,
                    newEvaluation.getEvaluation(),
                    newEvaluation.getComment(),
                    newEvaluation.getTimestamp());
        } else {
            throw new InvalidStudentFormException("Invalid evaluation form values.");
        }
    }

    public GetEvaluationDto updateStudentEvaluation(BindingResult bindingResult, Long evaluationId, Long studentId, AddUpdateEvaluationDto evaluationDto) {
        Long userId = ((LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        if (!bindingResult.hasErrors()) {
            evaluateFormValidator.validateEvaluation(evaluationDto);
            Evaluation evaluation = this.evaluationRepository.findById(evaluationId).orElseThrow(EvaluationNotFoundException::new);
            if (!studentId.equals(evaluation.getStudentId()) || !userId.equals(evaluation.getUserId())) {
                throw new EvaluationNotFoundException();
            }
            Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
            User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

            Evaluation newEvaluation = this.evaluationRepository.save(new Evaluation.EvaluationBuilder(student, user)
                    .setStream(evaluationDto.getStream())
                    .setCommunication(evaluationDto.getCommunication())
                    .setLearnAbility(evaluationDto.getLearnAbility())
                    .setDirection(evaluationDto.getDirection())
                    .setEvaluation(evaluationDto.getEvaluation())
                    .setComment(evaluationDto.getComment())
                    .setId(evaluationId)
                    .setIsActive((evaluationDto.getIsActive() != null) ? evaluationDto.getIsActive() : evaluation.isActive()).build());

            return new GetEvaluationDto(newEvaluation.getId(),
                    newEvaluation.isActive(),
                    newEvaluation.getStudentId(),
                    newEvaluation.getUserId(),
                    (newEvaluation.getStream() != null) ? newEvaluation.getStream().toString() : null,
                    (newEvaluation.getCommunication() != null) ? newEvaluation.getCommunication().toString() : null,
                    (newEvaluation.getDirection() != null) ? newEvaluation.getDirection().toString() : null,
                    (newEvaluation.getLearnAbility() != null) ? newEvaluation.getLearnAbility().toString() : null,
                    newEvaluation.getEvaluation(),
                    newEvaluation.getComment(),
                    newEvaluation.getTimestamp());
        } else {
            throw new InvalidStudentFormException("Invalid evaluation form values.");
        }
    }
}
