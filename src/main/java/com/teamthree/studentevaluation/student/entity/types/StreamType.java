package com.teamthree.studentevaluation.student.entity.types;

public enum StreamType implements Type {
    FE(0) {
        @Override
        public String toString() {
            return "FE";
        }
    },
    BE(1) {
        @Override
        public String toString() {
            return "BE";
        }
    },
    QA(2) {
        @Override
        public String toString() {
            return "QA";
        }
    },
    PROJECT(3) {
        @Override
        public String toString() {
            return "Project";
        }
    };

    private final int value;

    StreamType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
