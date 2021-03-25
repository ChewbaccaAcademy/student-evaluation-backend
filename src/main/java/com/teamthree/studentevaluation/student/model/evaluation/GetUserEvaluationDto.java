package com.teamthree.studentevaluation.student.model.evaluation;

import com.teamthree.studentevaluation.student.entity.Student;

public class GetUserEvaluationDto {

    private final Student student;

    private final GetEvaluationDto evaluation;

    public GetUserEvaluationDto(Student student, GetEvaluationDto evaluation) {
        this.student = student;
        this.evaluation = evaluation;
    }

    public Student getStudent() {
        return student;
    }

    public GetEvaluationDto getEvaluation() {
        return evaluation;
    }
}
