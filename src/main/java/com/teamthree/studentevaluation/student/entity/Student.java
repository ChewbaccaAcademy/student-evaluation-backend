package com.teamthree.studentevaluation.student.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank()
    @Size(min = 1, max = 256)
    private String name;

    @NotBlank()
    @Size(min = 1, max = 256)
    private String lastname;

    @Nullable
    private String university;

    @Nullable
    @Size(max = 250)
    private String comment;

    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public Student() {
    }

    public Student(String name, String lastname, String university, String comment) {
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
    }

    public Student(String name, String lastname, String university, String comment, Image image) {
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
        this.image = image;
    }

    public Student(Long id, String name, String lastname, String university, String comment, Image image) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.university = university;
        this.comment = comment;
        this.image = image;
    }

    public Long getId() {
        return id;
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
}
