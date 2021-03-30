package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.login.util.JwtUtil;
import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.EvaluationNotFoundException;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.evaluation.AddUpdateEvaluationDto;
import com.teamthree.studentevaluation.student.model.evaluation.GetEvaluationDto;
import com.teamthree.studentevaluation.student.model.evaluation.GetUserEvaluationDto;
import com.teamthree.studentevaluation.student.repository.EvaluationRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.EvaluateFormValidator;
import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.exceptions.UserNotFoundException;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<User> users = this.userRepository.findAll();

        return this.evaluationRepository.findAll().stream()
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .map(evaluation -> {
                    User evaluationUser = users.stream().filter(user -> user.getId().equals(evaluation.getUserId())).findFirst().orElse(null);
                    return new GetEvaluationDto(evaluation, evaluationUser);
                }).sorted((o1, o2) -> {
                    if (o1.getTimestamp() == null || o2.getTimestamp() == null) {
                        return 0;
                    }
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }).collect(Collectors.toList());
    }

    public List<GetEvaluationDto> getUserStudentEvaluations(Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return this.evaluationRepository.findByUser(user).orElseThrow(UserNotFoundException::new).stream()
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .map(evaluation -> new GetEvaluationDto(evaluation, user))
                .sorted((o1, o2) -> {
                    if (o1.getTimestamp() == null || o2.getTimestamp() == null) {
                        return 0;
                    }
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }).collect(Collectors.toList());
    }

    public List<GetUserEvaluationDto> getUserEvaluations() {
        Long userId = JwtUtil.getAuthenticatedUserId();
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Student> students = this.studentRepository.findAll();
        students.forEach(item -> {
            if (item.getImage() != null) {
                item.getImage().decompress();
            }
        });

        return this.evaluationRepository.findByUser(user).orElseThrow(UserNotFoundException::new).stream()
                .filter(evaluation -> {
                    boolean studentIsActive = students.stream().filter(student -> student.getId().equals(evaluation.getStudentId())).map(Student::isActive).findFirst().orElse(false);
                    return (!evaluation.isActive() && JwtUtil.isRequestUserAdmin() || evaluation.isActive()) && studentIsActive;
                })
                .map(evaluation -> new GetUserEvaluationDto(
                        students.stream().filter(item -> item.getId().equals(evaluation.getStudentId()))
                                .collect(Collectors.toList()).get(0),
                        new GetEvaluationDto(evaluation, user)
                )).sorted((o1, o2) -> {
                    if (o1.getEvaluation() != null && o1.getEvaluation().getTimestamp() == null || o2.getEvaluation() != null && o2.getEvaluation().getTimestamp() == null) {
                        return 0;
                    }
                    return o2.getEvaluation().getTimestamp().compareTo(o1.getEvaluation().getTimestamp());
                }).collect(Collectors.toList());
    }

    public List<GetEvaluationDto> getStudentEvaluationsById(Long studentId) {
        Student foundStudent = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        List<Evaluation> evaluations = this.evaluationRepository.findByStudent(foundStudent).orElseThrow(StudentNotFoundException::new);
        List<User> users = this.userRepository.findAll();

        return evaluations.stream()
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .map(evaluation -> {
                    User evaluationUser = users.stream().filter(item -> item.getId().equals(evaluation.getUserId())).findFirst().orElse(null);
                    return new GetEvaluationDto(evaluation, evaluationUser);
                }).sorted((o1, o2) -> {
                    if (o1.getTimestamp() == null || o2.getTimestamp() == null) {
                        return 0;
                    }
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }).collect(Collectors.toList());
    }

    public GetEvaluationDto getEvaluationById(Long id) {
        Evaluation evaluation = this.evaluationRepository.findById(id)
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .orElseThrow(EvaluationNotFoundException::new);
        User user = this.userRepository.findById(evaluation.getUserId()).orElse(null);

        return new GetEvaluationDto(evaluation, user);
    }

    public GetEvaluationDto addStudentEvaluation(BindingResult bindingResult, Long studentId, AddUpdateEvaluationDto evaluationDto) {
        if (!bindingResult.hasErrors()) {
            evaluateFormValidator.validateEvaluation(evaluationDto);
            Long userId = JwtUtil.getAuthenticatedUserId();
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

            return new GetEvaluationDto(newEvaluation, user);
        } else {
            throw new InvalidStudentFormException("Invalid evaluation form values.");
        }
    }

    public GetEvaluationDto updateStudentEvaluation(BindingResult bindingResult, Long evaluationId, Long studentId, AddUpdateEvaluationDto evaluationDto) {
        if (!bindingResult.hasErrors()) {
            evaluateFormValidator.validateEvaluation(evaluationDto);
            Evaluation evaluation = this.evaluationRepository.findById(evaluationId).orElseThrow(EvaluationNotFoundException::new);
            Long userId = JwtUtil.getAuthenticatedUserId();
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

            return new GetEvaluationDto(newEvaluation, user);
        } else {
            throw new InvalidStudentFormException("Invalid evaluation form values.");
        }
    }

    public void disableEvaluation(Long evaluationId) {
        Evaluation evaluation = this.evaluationRepository.findById(evaluationId).orElseThrow(EvaluationNotFoundException::new);
        Student student = this.studentRepository.findById(evaluation.getStudentId()).orElse(null);
        User user = this.userRepository.findById(evaluation.getUserId()).orElse(null);

        if (JwtUtil.isRequestUserAdmin() || JwtUtil.getAuthenticatedUserId().equals(evaluation.getUserId())) {
            this.evaluationRepository.save(new Evaluation.EvaluationBuilder()
                    .setId(evaluation.getId())
                    .setStudent(student)
                    .setUser(user)
                    .setIsActive(false)
                    .setEvaluation(evaluation.getEvaluation())
                    .setComment(evaluation.getComment())
                    .setStream(evaluation.getStream())
                    .setDirection(evaluation.getDirection())
                    .setLearnAbility(evaluation.getLearnAbility())
                    .setCommunication(evaluation.getCommunication()).build());
        } else {
            throw new AuthorizationServiceException("Could not update different user evaluation.");
        }
    }

}
