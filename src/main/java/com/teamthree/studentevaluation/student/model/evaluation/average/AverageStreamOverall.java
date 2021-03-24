package com.teamthree.studentevaluation.student.model.evaluation.average;

public class AverageStreamOverall {

    private final double fe;

    private final double be;

    private final double qa;

    private final double project;

    public AverageStreamOverall(double fe, double be, double qa, double project) {
        this.fe = fe;
        this.be = be;
        this.qa = qa;
        this.project = project;
    }

    public double getFe() {
        return fe;
    }

    public double getBe() {
        return be;
    }

    public double getQa() {
        return qa;
    }

    public double getProject() {
        return project;
    }
}