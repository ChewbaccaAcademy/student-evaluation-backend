package com.teamthree.studentevaluation.student.entity.types;

public enum Communication {
    ACTIVE("Is active"),
    PASSIVE("Is passive"),
    COMMUNICATIVE("Communicative"),
    WRITTEN("Prefers written communication over verbal");

    private final String type;

    Communication(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
