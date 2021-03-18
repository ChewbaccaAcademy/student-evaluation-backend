package com.teamthree.studentevaluation.student.model;

public class GetEvaluationDto {

    private Long id;

    private Long studentId;

    private Long userId;

    private String stream;

    private String communication;

    private String learnAbility;

    private String direction;

    private Integer evaluation;

    private String comment;

    public GetEvaluationDto(Long id, Long studentId, Long userId, String stream, String communication, String learnAbility, String direction, Integer evaluation, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.userId = userId;
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
}
