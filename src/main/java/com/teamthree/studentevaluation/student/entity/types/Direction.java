package com.teamthree.studentevaluation.student.entity.types;

public enum Direction implements Type {
    JAVA(0) {
        @Override
        public String toString() {
            return "Java";
        }
    },
    ANGULAR(1) {
        @Override
        public String toString() {
            return "Angular";
        }
    },
    TESTING(2) {
        @Override
        public String toString() {
            return "Testing";
        }
    },
    OTHER(3) {
        @Override
        public String toString() {
            return "Other";
        }
    };

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
