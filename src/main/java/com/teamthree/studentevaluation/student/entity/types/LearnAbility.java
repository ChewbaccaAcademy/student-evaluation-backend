package com.teamthree.studentevaluation.student.entity.types;

public enum LearnAbility implements Type {
    GOOD(0) {
        @Override
        public String toString() {
            return "Is able to adapt to changing topics quickly";
        }
    },
    BAD(1) {
        @Override
        public String toString() {
            return "Doesn't understand and does nothing about it";
        }
    },
    NORMAL(2) {
        @Override
        public String toString() {
            return "Doesn't understand but asks, tries to learn from mistakes";
        }
    };

    private final int value;

    LearnAbility(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
