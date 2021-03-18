package com.teamthree.studentevaluation.student.entity.types;

public enum Direction {
    JAVA("Java"),
    ANGULAR("Angular"),
    TESTING("Testing"),
    OTHER("Other");

    private final String type;

    Direction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
