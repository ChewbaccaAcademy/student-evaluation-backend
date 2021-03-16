package com.teamthree.studentevaluation.student.validators;

public abstract class Validator<T> {
    public abstract void validate(T attribute, String message);
}
