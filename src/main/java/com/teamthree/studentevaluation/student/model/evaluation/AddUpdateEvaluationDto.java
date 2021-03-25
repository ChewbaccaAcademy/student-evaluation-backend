package com.teamthree.studentevaluation.student.model.evaluation;

import com.teamthree.studentevaluation.student.entity.types.Communication;
import com.teamthree.studentevaluation.student.entity.types.Direction;
import com.teamthree.studentevaluation.student.entity.types.LearnAbility;
import com.teamthree.studentevaluation.student.entity.types.StreamType;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AddUpdateEvaluationDto {

    @Nullable
    @Column(name = "active")
    private final Boolean isActive;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private final StreamType stream;

    @Enumerated(EnumType.ORDINAL)
    private final Communication communication;

    @Enumerated(EnumType.ORDINAL)
    private final LearnAbility learnAbility;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private final Direction direction;

    @NotNull
    private final Integer evaluation;

    @Nullable
    @Size(max = 250)
    private final String comment;

    private final Timestamp timestamp;

    public AddUpdateEvaluationDto(@Nullable Boolean isActive, StreamType stream, Communication communication, LearnAbility learnAbility, Direction direction, @NotNull Integer evaluation, @Nullable @Size(max = 250) String comment, Timestamp timestamp) {
        this.isActive = isActive;
        this.stream = stream;
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.evaluation = evaluation;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    @Nullable
    public Boolean getIsActive() {
        return isActive;
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

    @Nullable
    public String getComment() {
        return comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
