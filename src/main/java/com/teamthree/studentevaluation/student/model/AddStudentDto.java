package com.teamthree.studentevaluation.student.model;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddStudentDto {

    @NotNull
    @NotBlank(message = "Student name is mandatory.")
    @Size(min = 1, max = 256)
    private final String name;

    @NotBlank(message = "Student lastname is mandatory.")
    @Size(min = 1, max = 256)
    private final String lastname;

    @Nullable
    private final String university;

    @Nullable
    private final String comment;

    public AddStudentDto(String name, String lastname, String university, String comment) {
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUniversity() {
        return university;
    }

    public String getComment() {
        return comment;
    }

}
