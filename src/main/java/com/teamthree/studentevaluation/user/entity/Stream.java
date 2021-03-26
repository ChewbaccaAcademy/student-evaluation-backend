package com.teamthree.studentevaluation.user.entity;

public enum Stream {
    FRONTEND(new Role(2)),
    BACKEND(new Role(2)),
    TESTING(new Role(2)),
    PROJECT(new Role(2));

    private final Role roleCode;

    Stream(Role roleCode) {
        this.roleCode = roleCode;
    }

    public Role getRoleCode() {
        return roleCode;
    }

}
