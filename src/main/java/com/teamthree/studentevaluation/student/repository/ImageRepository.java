package com.teamthree.studentevaluation.student.repository;

import com.teamthree.studentevaluation.student.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
