package com.teamthree.studentevaluation.student.model;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.model.evaluation.AverageEvaluation;
import org.apache.commons.lang3.mutable.MutableDouble;

public class EvaluationAverager {

    public MutableDouble communicationCount = new MutableDouble(0);
    public MutableDouble communicationTotal = new MutableDouble(0);
    public MutableDouble learnAbilityCount = new MutableDouble(0);
    public MutableDouble learnAbilityTotal = new MutableDouble(0);
    public MutableDouble directionCount = new MutableDouble(0);
    public MutableDouble directionTotal = new MutableDouble(0);
    public MutableDouble bestreamEvaluationCount = new MutableDouble(0);
    public MutableDouble bestreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble festreamEvaluationCount = new MutableDouble(0);
    public MutableDouble festreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble qastreamEvaluationCount = new MutableDouble(0);
    public MutableDouble qastreamEvaluationTotal = new MutableDouble(0);
    public MutableDouble projectbestreamEvaluationCount = new MutableDouble(0);
    public MutableDouble projectbestreamEvaluationTotal = new MutableDouble(0);

    public AverageEvaluation average() {
        return new AverageEvaluation(
                calculateAverage(communicationCount, communicationTotal),
                calculateAverage(learnAbilityCount, learnAbilityTotal),
                calculateAverage(directionCount, directionTotal),
                new AverageStreamOverall(
                        calculateAverage(festreamEvaluationCount, festreamEvaluationTotal),
                        calculateAverage(bestreamEvaluationCount, bestreamEvaluationTotal),
                        calculateAverage(qastreamEvaluationCount, qastreamEvaluationTotal),
                        calculateAverage(projectbestreamEvaluationCount, projectbestreamEvaluationTotal)
                )
        );
    }

    public void accept(Evaluation other) {
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
            if (other.getStream().toString().equals("FE")) {
                bestreamEvaluationCount.increment();
                bestreamEvaluationTotal = new MutableDouble(bestreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (other.getStream().toString().equals("BE")) {
                festreamEvaluationCount.increment();
                festreamEvaluationTotal = new MutableDouble(festreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (other.getStream().toString().equals("QA")) {
                qastreamEvaluationCount.increment();
                qastreamEvaluationTotal = new MutableDouble(qastreamEvaluationTotal.toDouble() + other.getEvaluation());
            } else if (other.getStream().toString().equals("Project")) {
                projectbestreamEvaluationCount.increment();
                projectbestreamEvaluationTotal = new MutableDouble(projectbestreamEvaluationTotal.toDouble() + other.getEvaluation());
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

        bestreamEvaluationCount = new MutableDouble(bestreamEvaluationCount.toDouble() + other.bestreamEvaluationCount.toDouble());
        bestreamEvaluationTotal = new MutableDouble(bestreamEvaluationTotal.toDouble() + other.bestreamEvaluationTotal.toDouble());

        festreamEvaluationCount = new MutableDouble(festreamEvaluationCount.toDouble() + other.festreamEvaluationCount.toDouble());
        festreamEvaluationTotal = new MutableDouble(festreamEvaluationTotal.toDouble() + other.festreamEvaluationTotal.toDouble());

        qastreamEvaluationCount = new MutableDouble(qastreamEvaluationCount.toDouble() + other.qastreamEvaluationCount.toDouble());
        qastreamEvaluationTotal = new MutableDouble(qastreamEvaluationTotal.toDouble() + other.qastreamEvaluationTotal.toDouble());

        projectbestreamEvaluationCount = new MutableDouble(projectbestreamEvaluationCount.toDouble() + other.projectbestreamEvaluationCount.toDouble());
        projectbestreamEvaluationTotal = new MutableDouble(projectbestreamEvaluationTotal.toDouble() + other.projectbestreamEvaluationTotal.toDouble());
    }

    private double calculateAverage(MutableDouble count, MutableDouble total) {
        return count.toDouble() > 0 ? (Math.round((total.toDouble()/count.toDouble())*100.0)/100.0) : 0;
    }
}
