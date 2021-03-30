package com.teamthree.studentevaluation.student.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamthree.studentevaluation.student.entity.types.Communication;
import com.teamthree.studentevaluation.student.entity.types.Direction;
import com.teamthree.studentevaluation.student.entity.types.LearnAbility;
import com.teamthree.studentevaluation.student.entity.types.StreamType;
import com.teamthree.studentevaluation.user.entity.User;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "evaluation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Value("${is.active:true}")
    @Column(name = "active", columnDefinition = "boolean default true")
    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    private User user;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "stream")
    private StreamType stream;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Communication communication;

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

    @Column(name = "timestamp", nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Timestamp timestamp;

    public Evaluation() {
    }

    private Evaluation(EvaluationBuilder builder) {
        this.id = builder.id;
        this.isActive = builder.isActive;
        this.student = builder.student;
        this.user = builder.user;
        this.stream = builder.stream;
        this.communication = builder.communication;
        this.learnAbility = builder.learnAbility;
        this.direction = builder.direction;
        this.evaluation = builder.evaluation;
        this.comment = builder.comment;
    }

    @PrePersist
    protected void onEvaluation() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("isActive")
    public Boolean isActive() {
        return isActive;
    }

    public Long getStudentId() {
        return student.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    public StreamType getStream() {
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

    public static class EvaluationBuilder {
        private Long id;
        private Boolean isActive;
        private Student student;
        private User user;
        private StreamType stream;
        private Communication communication;
        private LearnAbility learnAbility;
        private Direction direction;
        private Integer evaluation;
        private String comment;

        public EvaluationBuilder() {
        }

        public EvaluationBuilder(Student student, User user) {
            this.student = student;
            this.user = user;
        }

        public EvaluationBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public EvaluationBuilder setIsActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public EvaluationBuilder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public EvaluationBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public EvaluationBuilder setStream(StreamType stream) {
            this.stream = stream;
            return this;
        }

        public EvaluationBuilder setCommunication(Communication communication) {
            this.communication = communication;
            return this;
        }

        public EvaluationBuilder setLearnAbility(LearnAbility learnAbility) {
            this.learnAbility = learnAbility;
            return this;
        }

        public EvaluationBuilder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public EvaluationBuilder setEvaluation(Integer evaluation) {
            this.evaluation = evaluation;
            return this;
        }

        public EvaluationBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Evaluation build() {
            return new Evaluation(this);
        }
    }
}
