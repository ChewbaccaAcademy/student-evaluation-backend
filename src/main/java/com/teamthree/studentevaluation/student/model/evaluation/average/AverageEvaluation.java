package com.teamthree.studentevaluation.student.model.evaluation.average;

public class AverageEvaluation {

    private final double communication;

    private final double learnAbility;

    private final double direction;

    private final AverageStreamOverall streamOverall;

    public AverageEvaluation(double communication, double learnAbility, double direction, AverageStreamOverall streamOverall) {
        this.communication = communication;
        this.learnAbility = learnAbility;
        this.direction = direction;
        this.streamOverall = streamOverall;
    }

    public double getCommunication() {
        return communication;
    }

    public double getLearnAbility() {
        return learnAbility;
    }

    public double getDirection() {
        return direction;
    }

    public AverageStreamOverall getStreamOverall() {
        return streamOverall;
    }
}
