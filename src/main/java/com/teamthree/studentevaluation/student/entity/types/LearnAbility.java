package com.teamthree.studentevaluation.student.entity.types;

public enum LearnAbility {
    GOOD("Is able to adapt to changing topics quickly"),
    NORMAL("Doesn't understand but asks, tries to learn from mistakes"),
    BAD("Doesn't understand and does nothing about it");

    private final String type;

    LearnAbility(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
