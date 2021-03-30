package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.login.util.JwtUtil;
import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.Image;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.exceptions.InvalidStudentFormException;
import com.teamthree.studentevaluation.student.exceptions.StudentNotFoundException;
import com.teamthree.studentevaluation.student.model.evaluation.average.EvaluationAverager;
import com.teamthree.studentevaluation.student.model.student.AddStudentDto;
import com.teamthree.studentevaluation.student.model.student.GetStudentWithAverageDto;
import com.teamthree.studentevaluation.student.model.student.UpdateStudentDto;
import com.teamthree.studentevaluation.student.repository.EvaluationRepository;
import com.teamthree.studentevaluation.student.repository.ImageRepository;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import com.teamthree.studentevaluation.student.validators.ImageFormatValidator;
import com.teamthree.studentevaluation.student.validators.StudentValidator;
import com.teamthree.studentevaluation.user.entity.User;
import com.teamthree.studentevaluation.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.teamthree.studentevaluation.student.helper.ImageUtil.compressBytes;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ImageRepository imageRepository;
    private final StudentValidator studentValidator;
    private final ImageFormatValidator imageFormatValidator;
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, ImageRepository imageRepository, StudentValidator studentValidator, ImageFormatValidator imageFormatValidator, EvaluationRepository evaluationRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.imageRepository = imageRepository;
        this.studentValidator = studentValidator;
        this.imageFormatValidator = imageFormatValidator;
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }

    public List<GetStudentWithAverageDto> getAllStudent() {
        List<Student> students = this.studentRepository.findAll().stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
        students.forEach(student -> {
            if (student.getImage() != null)
                student.getImage().decompress();
        });

        List<Evaluation> evaluations = this.evaluationRepository.findAll();
        List<GetStudentWithAverageDto> foundStudents = students.stream()
                .map(student -> new GetStudentWithAverageDto(
                        student.getId(),
                        student.isActive(),
                        student.getName(),
                        student.getLastname(),
                        student.getUniversity(),
                        student.getComment(),
                        student.getImage(),
                        evaluations.stream()
                                .filter(item -> item.isActive() && item.getStudentId().equals(student.getId()))
                                .collect(EvaluationAverager::new, EvaluationAverager::acceptEvaluation, EvaluationAverager::combine).average()
                ))
                .collect(Collectors.toList());
        Collections.reverse(foundStudents);
        return foundStudents;
    }

    public GetStudentWithAverageDto getStudentById(Long id) {
        Student student = this.studentRepository.findById(id)
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .orElseThrow(StudentNotFoundException::new);
        if (student.getImage() != null) {
            student.getImage().decompress();
        }
        List<Evaluation> evaluations = this.evaluationRepository.findByStudent(student).orElse(Collections.emptyList());

        return new GetStudentWithAverageDto(
                student.getId(),
                student.isActive(),
                student.getName(),
                student.getLastname(),
                student.getUniversity(),
                student.getComment(),
                student.getImage(),
                evaluations.stream()
                        .filter(Evaluation::isActive)
                        .collect(EvaluationAverager::new, EvaluationAverager::acceptEvaluation, EvaluationAverager::combine).average()
        );
    }

    public Student addStudent(BindingResult bindingResult, AddStudentDto studentDto, MultipartFile imageFile) {
        if (bindingResult.hasErrors()) {
            throw new InvalidStudentFormException("Invalid student form.");
        }

        this.studentValidator.validateStudentToAdd(studentDto.getName(), studentDto.getLastname());
        Image newImage = null;

        try {
            if (imageFile != null) {
                this.imageFormatValidator.validate(imageFile, "Incorrect image type. Should be: (png/jpg/jpeg)");
                Image img = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(),
                        compressBytes(imageFile.getBytes()));
                newImage = this.imageRepository.save(img);
            }
        } catch (IOException e) {
            throw new InvalidStudentFormException("Failed to upload image.");
        }

        return this.studentRepository.save(new Student(
                true,
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

    public Student updateStudent(BindingResult bindingResult, Long id, UpdateStudentDto studentDto, MultipartFile imageFile) {
        if (bindingResult.hasErrors()) {
            throw new InvalidStudentFormException("Invalid student form.");
        }

        Student student = this.studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        this.studentValidator.validateStudentToUpdate(id, studentDto);
        Image newImage = student.getImage();

        try {
            if (imageFile != null) {
                if (newImage == null) {
                    newImage = new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                } else {
                    newImage = new Image(newImage.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), compressBytes(imageFile.getBytes()));
                }
                newImage = this.imageRepository.save(newImage);
            }
        } catch (IOException e) {
            throw new InvalidStudentFormException("Failed to upload image.");
        }

        return this.studentRepository.save(new Student(
                id,
                (studentDto.getIsActive() != null) ? studentDto.getIsActive() : student.isActive(),
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getUniversity(),
                studentDto.getComment(),
                newImage));
    }

    public void disableStudent(Long studentId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        this.evaluationRepository.findByStudent(student).ifPresent(studentEvaluations -> studentEvaluations.forEach(evaluation -> {
            User evaluationUser = this.userRepository.getOne(evaluation.getUserId());
            Evaluation disabledEvaluation = new Evaluation.EvaluationBuilder()
                    .setIsActive(false)
                    .setUser(evaluationUser)
                    .setId(evaluation.getId())
                    .setEvaluation(evaluation.getEvaluation())
                    .setLearnAbility(evaluation.getLearnAbility())
                    .setComment(evaluation.getComment())
                    .setCommunication(evaluation.getCommunication())
                    .setStudent(student)
                    .setStream(evaluation.getStream())
                    .setDirection(evaluation.getDirection()).build();
            this.evaluationRepository.save(disabledEvaluation);
        }));
        this.studentRepository.save(new Student(
                student.getId(),
                false,
                student.getName(),
                student.getLastname(),
                student.getUniversity(),
                student.getComment(),
                student.getImage()));
    }

}
