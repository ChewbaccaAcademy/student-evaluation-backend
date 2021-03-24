package com.teamthree.studentevaluation.student.model.student;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamthree.studentevaluation.student.entity.Image;
import com.teamthree.studentevaluation.student.model.evaluation.average.AverageEvaluation;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetStudentWithAverageDto {

    private final Long id;

    private final Boolean isActive;

    private final String name;

    private final String lastname;

    private final String university;

    private final String comment;

    private final Image image;

    private final AverageEvaluation averageEvaluation;

    public GetStudentWithAverageDto(Long id, boolean isActive, String name, String lastname, String university, String comment, Image image, AverageEvaluation averageEvaluation) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
        this.image = image;
        this.averageEvaluation = averageEvaluation;
    }

    public Long getId() {
        return id;
    }

    @JsonProperty("isActive")
    public Boolean isActive() {
        return isActive;
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

    public Image getImage() {
        return image;
    }

    public AverageEvaluation getAverageEvaluationDetails() {
        return averageEvaluation;
    }
}
