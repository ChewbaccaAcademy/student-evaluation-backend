package com.teamthree.studentevaluation.student.model;

import com.teamthree.studentevaluation.student.entity.types.Communication;
import com.teamthree.studentevaluation.student.entity.types.Direction;
import com.teamthree.studentevaluation.student.entity.types.LearnAbility;
import com.teamthree.studentevaluation.student.entity.types.Stream;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AddUpdateEvaluationDto {

    @Enumerated(EnumType.STRING)
    private final Stream stream;

    @Enumerated(EnumType.ORDINAL)
    private final Communication communication;

    @Enumerated(EnumType.ORDINAL)
    private final LearnAbility learnAbility;

    @Enumerated(EnumType.ORDINAL)
    private final Direction direction;

    @NotNull
    private final Integer evaluation;

    @Size(max = 250)
    private final String comment;

    private final Timestamp timestamp;

    public AddUpdateEvaluationDto(Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, @NotNull Integer evaluation, @Size(max = 250) String comment, Timestamp timestamp) {
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
        this.timestamp = timestamp;
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
