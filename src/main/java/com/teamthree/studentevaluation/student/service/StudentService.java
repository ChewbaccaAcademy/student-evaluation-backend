package com.teamthree.studentevaluation.student.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Autowired
    public StudentService(StudentRepository studentRepository, ImageRepository imageRepository, StudentValidator studentValidator, ImageFormatValidator imageFormatValidator, EvaluationRepository evaluationRepository) {
        this.studentRepository = studentRepository;
        this.imageRepository = imageRepository;
        this.studentValidator = studentValidator;
        this.imageFormatValidator = imageFormatValidator;
        this.evaluationRepository = evaluationRepository;
    }

    public List<GetStudentWithAverageDto> getAllStudent() {
        List<Student> students = this.studentRepository.findAll();
        students.forEach(student -> {
            if (student.getImage() != null)
                student.getImage().decompress();
        });

        List<Evaluation> evaluations = this.evaluationRepository.findAll();

        return students.stream()
                .map(s -> new GetStudentWithAverageDto(
                        s.getId(),
                        s.isActive(),
                        s.getName(),
                        s.getLastname(),
                        s.getUniversity(),
                        s.getComment(),
                        s.getImage(),
                        evaluations.stream().filter(x -> x.getStudentId() == s.getId())
                                .collect(EvaluationAverager::new, EvaluationAverager::acceptEvaluation, EvaluationAverager::combine).average()
                ))
                .collect(Collectors.toList());
    }

    public GetStudentWithAverageDto getStudentById(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
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
                evaluations.stream().collect(EvaluationAverager::new, EvaluationAverager::acceptEvaluation, EvaluationAverager::combine).average()
        );
    }

    public Student addStudent(AddStudentDto studentDto, MultipartFile imageFile) {
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

    public Student updateStudent(Long id, UpdateStudentDto studentDto, MultipartFile imageFile) {
        Student student = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
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

}
