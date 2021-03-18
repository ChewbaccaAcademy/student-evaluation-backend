package com.teamthree.studentevaluation.student.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.teamthree.studentevaluation.student.entity.types.*;
import com.teamthree.studentevaluation.user.entity.User;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Stream stream;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Communication communication;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private LearnAbility learnAbility;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Direction direction;

    @NotNull
    @Value("${evaluation: 0}")
    @Min(value = 1, message = "Student evaluation should be between 1 and 5.")
    @Max(value = 5, message = "Student evaluation should be between 1 and 5.")
    private Integer evaluation;

    @Size(max = 250)
    private String comment;

    public Evaluation() {
    }

    public Evaluation(Student student, User user, Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, Integer evaluation, String comment) {
        this.student = student;
        this.user = user;
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return student.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    public Stream getStream() {
        return stream;
    }

    public Communication getCommunication() {
        return communication;
    }

    public LearnAbility getLearnAbility() {
        return learnAbility;
    }

    public Direction getDirection() {
        return direction;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public String getComment() {
        return comment;
    }
}
