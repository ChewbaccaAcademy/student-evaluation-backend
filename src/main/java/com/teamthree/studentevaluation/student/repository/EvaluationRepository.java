package com.teamthree.studentevaluation.student.repository;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    Optional<List<Evaluation>> findByStudent(Student student);
}
