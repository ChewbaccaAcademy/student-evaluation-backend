package com.teamthree.studentevaluation.student.model;

import java.sql.Timestamp;

public class GetEvaluationDto {

    private final Long id;

    private final Boolean isActive;

    private final Long studentId;

    private final Long userId;

    private final String stream;

    private final String communication;

    private final String learnAbility;

    private final String direction;

    private final Integer evaluation;

    private final String comment;

    private final Timestamp timestamp;

    public GetEvaluationDto(Long id, Boolean isActive, Long studentId, Long userId, String stream, String communication, String learnAbility, String direction, Integer evaluation, String comment, Timestamp timestamp) {
        this.id = id;
        this.isActive = isActive;
        this.studentId = studentId;
        this.userId = userId;
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

    public String getStream() {
        return stream;
    }

    public String getCommunication() {
        return communication;
    }

    public String getLearnAbility() {
        return learnAbility;
    }

    public String getDirection() {
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
