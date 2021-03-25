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

    private final StudentRepository studentRepository;

    @Autowired
    public SearchStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsByValue(String value) {
        String searchMessage = value.toLowerCase(Locale.ROOT);
        String[] valueArray = value.split(" ");
        List<Student> studentList = this.studentRepository.findAll().stream()
                .filter(item -> !item.isActive() && JwtUtil.isRequestUserAdmin() || item.isActive())
                .filter(s -> ArrayUtils.contains(valueArray, s.getName().toLowerCase()) || ArrayUtils.contains(valueArray, s.getLastname().toLowerCase())
                        || (s.getName() + " " + s.getLastname()).toLowerCase(Locale.ROOT).contains(searchMessage))
                .collect(Collectors.toList());
        studentList.forEach(student -> {
            if (student.getImage() != null) {
                student.getImage().decompress();
            }
        });
        return studentList;
    }
}
