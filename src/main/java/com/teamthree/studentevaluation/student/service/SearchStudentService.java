package com.teamthree.studentevaluation.student.service;

import com.teamthree.studentevaluation.login.util.JwtUtil;
import com.teamthree.studentevaluation.student.entity.Student;
import com.teamthree.studentevaluation.student.repository.StudentRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
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
        String searchMessage = value.toLowerCase(Locale.ROOT);
        String[] values = value.split(SPACE);
        List<Student> students = this.studentRepository.findAll().stream()
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .filter(s -> ArrayUtils.contains(values, s.getName().toLowerCase()) || ArrayUtils.contains(values, s.getLastname().toLowerCase())
                        || (s.getName() + SPACE + s.getLastname()).toLowerCase(Locale.ROOT).contains(searchMessage))
                .collect(Collectors.toList());
        students.forEach(s -> {
            if (s.getImage() != null) {
                s.getImage().decompress();
            }
        });
        return students;
    }
}
