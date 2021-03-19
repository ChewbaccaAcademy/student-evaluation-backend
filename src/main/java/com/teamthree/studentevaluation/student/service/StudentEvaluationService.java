package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.EvaluationNotFoundException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.EvaluationDto;
import com.teamthree.studentevaluation.student.model.GetEvaluationDto;
import com.teamthree.studentevaluation.student.repository.EvaluationRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.EvaluateFormValidator;
import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.exceptions.UserNotFoundException;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Evaluation> getStudentEvaluations() {
        return this.evaluationRepository.findAll();
    }

    public List<GetEvaluationDto> getStudentEvaluationsById(Long id) {
        Student foundStudent = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        List<Evaluation> evaluations = this.evaluationRepository.findByStudent(foundStudent).orElseThrow(StudentNotFoundException::new);
        return evaluations.stream().map(evaluation -> new GetEvaluationDto(
                evaluation.getId(),
                evaluation.getStudentId(),
                evaluation.getUserId(),
                evaluation.getStream().toString(),
                evaluation.getCommunication().toString(),
                evaluation.getDirection().toString(),
                evaluation.getLearnAbility().toString(),
                evaluation.getEvaluation(),
                evaluation.getComment())).collect(Collectors.toList());
    }

    public Evaluation addStudentEvaluation(Long studentId, Long userId, EvaluationDto evaluationDto) {
        evaluateFormValidator.validate(evaluationDto, "Student evaluation should be between 1 and 5.");
        Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return this.evaluationRepository.save(new Evaluation(student,
                user,
                evaluationDto.getStream(),
                evaluationDto.getCommunication(),
                evaluationDto.getLearnAbility(),
                evaluationDto.getDirection(),
                evaluationDto.getEvaluation(),
                evaluationDto.getComment())
        );
    }

    public Evaluation updateStudentEvaluation(Long evaluationId, Long studentId, Long userId, EvaluationDto evaluationDto) {
        evaluateFormValidator.validate(evaluationDto, "Invalid updated evaluation form."); //papildyti validation
        Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Evaluation evaluation = this.evaluationRepository.findById(evaluationId).orElseThrow(EvaluationNotFoundException::new);
        if (!studentId.equals(evaluation.getStudentId()) || !userId.equals(evaluation.getUserId())) {
            throw new EvaluationNotFoundException();
        }

        return this.evaluationRepository.save(new Evaluation(evaluation.getId(),
                student,
                user,
                evaluationDto.getStream(),
                evaluationDto.getCommunication(),
                evaluationDto.getLearnAbility(),
                evaluationDto.getDirection(),
                evaluationDto.getEvaluation(),
                evaluationDto.getComment())
        );
    }

    public void deleteStudentEvaluation(Long evaluationId, Long studentId, Long userId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        User user = this.userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Evaluation evaluation = this.evaluationRepository.findById(evaluationId).orElseThrow(EvaluationNotFoundException::new);
        if (!studentId.equals(evaluation.getStudentId()) || !userId.equals(evaluation.getUserId())) {
            throw new EvaluationNotFoundException();
        }
        this.evaluationRepository.delete(evaluation);
    }

}
