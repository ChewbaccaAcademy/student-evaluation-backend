package com.teamthree.studentevaluation.student.model.evaluation;

import com.teamthree.studentevaluation.student.entity.Evaluation;
import com.teamthree.studentevaluation.student.model.EvaluationAverager;

import java.util.List;

public class EvaluationOverallAverageDto {

    private AverageEvaluation averageEvaluation;

    public EvaluationOverallAverageDto(List<Evaluation> evaluations) {
        this.averageEvaluation = evaluations.stream().collect(EvaluationAverager::new, EvaluationAverager::accept, EvaluationAverager::combine).average();
    }

    public AverageEvaluation getEvaluationsAverage() {
        return averageEvaluation;
    }
}
