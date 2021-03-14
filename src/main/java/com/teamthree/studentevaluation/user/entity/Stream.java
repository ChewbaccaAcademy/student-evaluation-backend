package com.teamthree.studentevaluation.user.entity;

public enum Stream {
    FRONTEND(0),
    BACKEND(1),
    TESTING(2),
    PROJECT(3);

    private final int roleCode;

    public int getRoleCode() {
        return roleCode;
    }

    Stream(int roleCode) {
        this.roleCode = roleCode;
    }

}
