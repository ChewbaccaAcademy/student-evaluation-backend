package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.login.util.JwtUtil;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchStudentService {

    private static final String SPACE = " ";
    private final StudentRepository studentRepository;

    @Autowired
    public SearchStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsByValue(String value) {
        String searchMessage = value.toLowerCase();
        String[] values = value.toLowerCase().split(SPACE);
        List<Student> students = this.studentRepository.findAll().stream()
                .filter(Student::isActive)
                .filter(item -> ArrayUtils.contains(values, item.getName().toLowerCase()) && ArrayUtils.contains(values, item.getLastname().toLowerCase())
                        || (item.getName() + SPACE + item.getLastname()).toLowerCase().contains(searchMessage)
                        || (item.getLastname() + SPACE + item.getName()).toLowerCase().contains(searchMessage))
                .collect(Collectors.toList());
        students.forEach(s -> {
            if (s.getImage() != null) {
                s.getImage().decompress();
            }
        });
        return students;
    }
}
