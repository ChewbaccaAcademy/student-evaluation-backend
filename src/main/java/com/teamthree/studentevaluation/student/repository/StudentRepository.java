package com.teamthree.studentevaluation.student.repository;

import com.teamthree.studentevaluation.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
