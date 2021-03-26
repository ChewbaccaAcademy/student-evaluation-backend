package com.teamthree.studentevaluation.student.model.evaluation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.user.entity.User;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEvaluationDto {

    private final Long id;

    private final Boolean isActive;

    private final Long studentId;

    private final Long userId;

    private final String userUsername;

    private final String userStream;

    private final String stream;

    private final Integer communication;

    private final Integer learnAbility;

    private final Integer direction;

    private final Integer evaluation;

    private final String comment;

    private final Timestamp timestamp;

    public GetEvaluationDto(Evaluation evaluation, User user) {
        this.id = evaluation.getId();
        this.isActive = evaluation.isActive();
        this.studentId = evaluation.getStudentId();
        this.userId = evaluation.getUserId();
        this.userUsername = (user != null) ? user.getUsername() : null;
        this.userStream = (user != null) ? user.getStream() : null;
        this.stream = evaluation.getStream().toString();
        this.communication = (evaluation.getCommunication() != null) ? evaluation.getCommunication().getValue() : null;
        this.learnAbility = (evaluation.getLearnAbility() != null) ? evaluation.getLearnAbility().getValue() : null;
        this.direction = evaluation.getDirection().getValue();
        this.evaluation = evaluation.getEvaluation();
        this.comment = evaluation.getComment();
        this.timestamp = evaluation.getTimestamp();
    }

    public Long getId() {
        return id;
    }

    public Boolean isActive() {
        return isActive;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserStream() {
        return userStream;
    }

    public String getStream() {
        return stream;
    }

    public Integer getCommunication() {
        return communication;
    }

    public Integer getLearnAbility() {
        return learnAbility;
    }

    public Integer getDirection() {
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
