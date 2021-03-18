package com.teamthree.studentevaluation.student.model;

import com.teamthree.studentevaluation.student.entity.types.Communication;
import com.teamthree.studentevaluation.student.entity.types.Direction;
import com.teamthree.studentevaluation.student.entity.types.LearnAbility;
import com.teamthree.studentevaluation.student.entity.types.Stream;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddEvaluationDto {

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

    public AddEvaluationDto(Stream stream, Communication communication, LearnAbility learnAbility, Direction direction, @NotNull Integer evaluation, @Size(max = 250) String comment) {
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
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
