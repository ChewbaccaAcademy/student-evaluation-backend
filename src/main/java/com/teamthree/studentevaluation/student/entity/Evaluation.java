package com.teamthree.studentevaluation.student.entity;

import com.teamthree.studentevaluation.student.entity.types.*;
import com.teamthree.studentevaluation.user.entity.User;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Stream stream;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Communication communication;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private LearnAbility learnAbility;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Direction direction;

    @NotNull
    private Integer evaluation;

    @Size(max = 250)
    private String comment;

    @Column(name = "timestamp", nullable = false, insertable = false, columnDefinition = "timestamp default current_timestamp")
    private Timestamp timestamp;

    @PrePersist
    protected void onEvaluation() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Evaluation() {
    }

    public Evaluation(Student student, User user, Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, Integer evaluation, String comment, Timestamp timestamp) {
        this.student = student;
        this.user = user;
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public Evaluation(Long id, Student student, User user, Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, Integer evaluation, String comment, Timestamp timestamp) {
        this.id = id;
        this.student = student;
        this.user = user;
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
        this.timestamp = timestamp;
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

    public Evaluation(Long id, Student student, User user, Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, Integer evaluation, String comment) {
        this.id = id;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
