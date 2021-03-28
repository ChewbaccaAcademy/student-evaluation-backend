package com.teamthree.studentevaluation.student.model.evaluation.average;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.entity.types.StreamType;
import org.apache.commons.lang3.mutable.MutableDouble;

public class EvaluationAverager {

    public MutableDouble communicationCount = new MutableDouble(0);
    public MutableDouble communicationTotal = new MutableDouble(0);
    public MutableDouble learnAbilityCount = new MutableDouble(0);
    public MutableDouble learnAbilityTotal = new MutableDouble(0);
    public MutableDouble directionCount = new MutableDouble(0);
    public MutableDouble directionTotal = new MutableDouble(0);
    public MutableDouble beStreamEvaluationCount = new MutableDouble(0);
    public MutableDouble beStreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble feStreamEvaluationCount = new MutableDouble(0);
    public MutableDouble feStreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble qaStreamEvaluationCount = new MutableDouble(0);
    public MutableDouble qaStreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble projectStreamEvaluationCount = new MutableDouble(0);
    public MutableDouble projectStreamEvaluationTotal = new MutableDouble(0);

    public AverageEvaluation average() {
        return new AverageEvaluation(
                calculateAverage(communicationCount, communicationTotal),
                calculateAverage(learnAbilityCount, learnAbilityTotal),
                calculateAverage(directionCount, directionTotal),
                new AverageStreamOverall(
                        calculateAverage(feStreamEvaluationCount, feStreamEvaluationTotal),
                        calculateAverage(beStreamEvaluationCount, beStreamEvaluationTotal),
                        calculateAverage(qaStreamEvaluationCount, qaStreamEvaluationTotal),
                        calculateAverage(projectStreamEvaluationCount, projectStreamEvaluationTotal)
                )
        );
    }

    public void acceptEvaluation(Evaluation other) {
        if (other.getCommunication() != null) {
            communicationCount.increment();
            communicationTotal = new MutableDouble(communicationTotal.toDouble() + other.getCommunication().getValue());
        }
        if (other.getLearnAbility() != null) {
            learnAbilityCount.increment();
            learnAbilityTotal = new MutableDouble(learnAbilityTotal.toDouble() + other.getLearnAbility().getValue());
        }
        if (other.getDirection() != null) {
            directionCount.increment();
            directionTotal = new MutableDouble(directionTotal.toDouble() + other.getDirection().getValue());
        }
        if (other.getStream() != null) {
            if (StreamType.FE.toString().equals(other.getStream().toString())) {
                feStreamEvaluationCount.increment();
                feStreamEvaluationTotal = new MutableDouble(feStreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (StreamType.BE.toString().equals(other.getStream().toString())) {
                beStreamEvaluationCount.increment();
                beStreamEvaluationTotal = new MutableDouble(beStreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (StreamType.QA.toString().equals(other.getStream().toString())) {
                qaStreamEvaluationCount.increment();
                qaStreamEvaluationTotal = new MutableDouble(qaStreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (StreamType.PROJECT.toString().equals(other.getStream().toString())) {
                projectStreamEvaluationCount.increment();
                projectStreamEvaluationTotal = new MutableDouble(projectStreamEvaluationTotal.toDouble() + other.getEvaluation());
            }
        }
    }

    public void combine(EvaluationAverager other) {
        communicationCount = new MutableDouble(communicationCount.toDouble() + other.communicationCount.toDouble());
        communicationTotal = new MutableDouble(communicationTotal.toDouble() + other.communicationTotal.toDouble());

        learnAbilityCount = new MutableDouble(learnAbilityCount.toDouble() + other.learnAbilityCount.toDouble());
        learnAbilityTotal = new MutableDouble(learnAbilityTotal.toDouble() + other.learnAbilityTotal.toDouble());

        directionCount = new MutableDouble(directionCount.toDouble() + other.directionCount.toDouble());
        directionTotal = new MutableDouble(directionTotal.toDouble() + other.directionTotal.toDouble());

        beStreamEvaluationCount = new MutableDouble(beStreamEvaluationCount.toDouble() + other.beStreamEvaluationCount.toDouble());
        beStreamEvaluationTotal = new MutableDouble(beStreamEvaluationTotal.toDouble() + other.beStreamEvaluationTotal.toDouble());

        feStreamEvaluationCount = new MutableDouble(feStreamEvaluationCount.toDouble() + other.feStreamEvaluationCount.toDouble());
        feStreamEvaluationTotal = new MutableDouble(feStreamEvaluationTotal.toDouble() + other.feStreamEvaluationTotal.toDouble());

        qaStreamEvaluationCount = new MutableDouble(qaStreamEvaluationCount.toDouble() + other.qaStreamEvaluationCount.toDouble());
        qaStreamEvaluationTotal = new MutableDouble(qaStreamEvaluationTotal.toDouble() + other.qaStreamEvaluationTotal.toDouble());

        projectStreamEvaluationCount = new MutableDouble(projectStreamEvaluationCount.toDouble() + other.projectStreamEvaluationCount.toDouble());
        projectStreamEvaluationTotal = new MutableDouble(projectStreamEvaluationTotal.toDouble() + other.projectStreamEvaluationTotal.toDouble());
    }

    private double calculateAverage(MutableDouble count, MutableDouble total) {
        return count.toDouble() > 0 ? (Math.round((total.toDouble() / count.toDouble()) * 100.0) / 100.0) : 0;
    }
}
