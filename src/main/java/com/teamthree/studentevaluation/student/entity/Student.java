package com.teamthree.studentevaluation.student.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "student")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "active")
    @JsonProperty(value = "isActive")
    private Boolean isActive;

    @NotBlank()
    @Size(min = 1, max = 256)
    private String name;

    @NotBlank()
    @Size(min = 1, max = 256)
    private String lastname;

    private String university;

    @Size(max = 250)
    private String comment;

    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "evaluation")
    private List<Evaluation> evaluation;

    public Student() {
    }

    public Student(boolean isActive, String name, String lastname, String university, String comment, Image image) {
        this.isActive = isActive;
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
        this.image = image;
    }

    public Student(Long id, boolean isActive, String name, String lastname, String university, String comment, Image image) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
        this.image = image;
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

    public List<Evaluation> getEvaluation() {
        return evaluation;
    }
}
