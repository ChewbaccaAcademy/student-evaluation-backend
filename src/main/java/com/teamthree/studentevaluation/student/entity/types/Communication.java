package com.teamthree.studentevaluation.student.entity.types;

public enum Communication implements Type {
    ACTIVE(0) {
        @Override
        public String toString() {
            return "Is active, communicative";
        }
    },
    PASSIVE(1) {
        @Override
        public String toString() {
            return "Is passive";
        }
    },
    WRITTEN(2) {
        @Override
        public String toString() {
            return "Prefers written communication over verbal";
        }
    };

    private final int value;

    Communication(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
