package com.teamthree.studentevaluation.student.model.evaluation;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEvaluationDto {

    private final Long id;

    private final Boolean isActive;

    private final Long studentId;

    private final Long userId;

    private final String userUsername;

    private final String userStream;

    private final Integer stream;

    private final Integer communication;

    private final Integer learnAbility;

    private final Integer direction;

    private final Integer evaluation;

    private final String comment;

    private final Timestamp timestamp;

    public GetEvaluationDto(Long id, Boolean isActive, Long studentId, Long userId, String userUsername, String userStream, Integer stream, Integer communication, Integer learnAbility, Integer direction, Integer evaluation, String comment, Timestamp timestamp) {
        this.id = id;
        this.isActive = isActive;
        this.studentId = studentId;
        this.userId = userId;
        this.userUsername = userUsername;
        this.userStream = userStream;
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
        this.timestamp = timestamp;
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

    public Integer getStream() {
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
