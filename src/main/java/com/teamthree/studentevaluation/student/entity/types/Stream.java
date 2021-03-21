package com.teamthree.studentevaluation.student.entity.types;

public enum Stream {
    FE("FE"),
    BE("BE"),
    QA("QA"),
    PROJECT("Project");

    private final String type;

    Stream(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
